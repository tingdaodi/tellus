package com.tellus.crud.support.condition;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tellus.support.annotation.IQueries;
import com.tellus.support.annotation.IUniqueness;
import com.tellus.support.enums.basic.FactorType;
import com.tellus.support.enums.basic.OptionType;
import com.tellus.toolkit.ExceptionUtils;
import com.tellus.toolkit.ReflectionKit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 构造条件工具
 *
 * @author Roy
 * @date 2020/5/26 0:15
 */
@Slf4j
public class FactorKit {

    /**
     * 最多支持两个值, between / not between
     */
    private static final Integer MULTI_CONDITION_SUPPORTED = 2;
    /**
     * 排除的字段
     */
    private static final Set<String> DEFAULT_EXCLUDES = Sets.newHashSet("serialVersionUID");

    public static <V, T> QueryWrapper<T> builderQueryWrapper(V info, Class<T> entityClass) {
        return builderQueryWrapper(info, DEFAULT_EXCLUDES);
    }

    public static <V, T> QueryWrapper<T> builderQueryWrapper(V info, Set<String> excludes) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        Map<String, List<Query>> queries = new HashMap<>(8);
        Class<?> infoClazz = info.getClass();
        Field[] fields = infoClazz.getDeclaredFields();

        if (null == excludes || excludes.isEmpty()) {
            excludes = Sets.newHashSet(DEFAULT_EXCLUDES);
        } else {
            excludes.addAll(DEFAULT_EXCLUDES);
        }

        for (Field field : fields) {
            String fieldName = field.getName();
            if (excludes.contains(fieldName)) {
                continue;
            }

            Object fieldValue = getFieldValue(field, info);
            IQueries iQueries = field.getAnnotation(IQueries.class);
            if (null != iQueries) {
                if (iQueries.exclude()) {
                    continue;
                }

                if (!Strings.isNullOrEmpty(iQueries.value())) {
                    fieldName = iQueries.value();
                }

                Query query;
                if (iQueries.type() == FactorType.OPERATOR) {
                    OptionType operatorType = (OptionType) getFieldValue(field, info);
                    query = Query.builder()
                            .fieldName(fieldName)
                            .value(fieldValue)
                            .factorType(FactorType.OPERATOR)
                            .optionType(operatorType)
                            .build();
                } else {
                    query = Query.builder()
                            .fieldName(fieldName)
                            .value(fieldValue)
                            .factorType(iQueries.type())
                            .optionType(iQueries.option())
                            .build();
                }

                if (queries.containsKey(fieldName)) {
                    queries.get(fieldName).add(query);
                } else {
                    queries.put(fieldName, Lists.newArrayList(query));
                }
            } else {
                FactorType factorType = getFactorType(field.getType());
                Query query = Query.builder()
                        .fieldName(fieldName)
                        .value(fieldValue)
                        .factorType(factorType)
                        .optionType(OptionType.EQ)
                        .build();
                queries.put(fieldName, Lists.newArrayList(query));
            }
        }

        if (!queries.isEmpty()) {
            List<Factor> factors = builderFactor(queries);
            factors.forEach(factor -> factor.handle(wrapper));
        }

        return wrapper;
    }

    public static <V, T> Map<IUniqueness, QueryWrapper<T>>
    builderQueryWrapperForUniqueness(V info) {
        Map<IUniqueness, QueryWrapper<T>> wrappers = Maps.newConcurrentMap();
        Class<?> infoClazz = info.getClass();
        Field[] fields = infoClazz.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            Object fieldValue = getFieldValue(field, info);

            if (null == fieldValue) {
                continue;
            }

            IUniqueness uniqueness = field.getAnnotation(IUniqueness.class);
            if (null != uniqueness) {
                if (!Strings.isNullOrEmpty(uniqueness.value())) {
                    fieldName = uniqueness.value();
                }

                FactorType factorType = getFactorType(field.getType());
                Query query = Query.builder()
                        .fieldName(fieldName)
                        .value(fieldValue)
                        .factorType(factorType)
                        .optionType(OptionType.EQ)
                        .build();

                QueryWrapper<T> queryWrapper = new QueryWrapper<>();
                builderFactor(query).handle(queryWrapper);

                wrappers.put(uniqueness, queryWrapper);
            }
        }
        return wrappers;
    }

    private static Factor builderFactor(Query query) {
        return builderFactor(ImmutableBiMap.of(query.fieldName, Lists.newArrayList(query))).get(0);
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    private static List<Factor> builderFactor(Map<String, List<Query>> queries) {
        List<Factor> factors = new ArrayList<>();
        queries.forEach((key, values) -> {
            //  过滤出操作符对象, 默认使用参数上的注解的操作符
            Optional<Query> operator = values.stream()
                    .filter(query -> query.factorType != FactorType.OPERATOR)
                    .findFirst();
            List<Query> valueQueries = values.stream()
                    .filter(query -> query.factorType != FactorType.OPERATOR)
                    .collect(Collectors.toList());
            if (valueQueries.isEmpty() || valueQueries.size() > MULTI_CONDITION_SUPPORTED) {
                log.warn("Supports up to two conditional queries, fieldName:" + key);
                return;
            }

            Query query = valueQueries.get(0);
            Object value = query.getValue();
            OptionType optionType = query.getOptionType();
            if (operator.isPresent() && null != operator.get().optionType) {
                optionType = operator.get().optionType;
            }

            if (isBetweenSupported(optionType) && valueQueries.size() != MULTI_CONDITION_SUPPORTED) {
                log.warn("Query is <between> option type is not configured with begin/end annotations, ex.: @IQuery(" +
                        "OptionType.BETWEEN) and @IQuery(OptionType.BETWEEN_END), fieldName:" + query.getFieldName());
                return;
            }

            Factor factor = builderFactor(query.factorType);
            if (!factor.supported(optionType)) {
                log.warn("[" + query.factorType + "] Unsupported operation type [" + optionType + "]");
                return;
            }

            factor.setFieldName(key);
            factor.setOptionType(optionType);
            factor.setValue(value);

            if (operator.isPresent() || valueQueries.size() != MULTI_CONDITION_SUPPORTED) {
                factors.add(factor);
                return;
            }

            Query other = valueQueries.get(1);
            Object otherValue = other.value;
            if (null == value && null == otherValue) {
                return;
            }
            if (null == otherValue) {
                factor.setOptionType(OptionType.GE);
                factor.setValue(value);
                factors.add(factor);
                return;
            }
            if (null == value) {
                factor.setOptionType(OptionType.LE);
                factor.setValue(otherValue);
                factors.add(factor);
                return;
            }

            if (factor instanceof MultiFactor && isBetweenSupported(optionType)) {
                MultiFactor multiFactor = (MultiFactor) factor;
                multiFactor.setOptionType(OptionType.BETWEEN);
                if (optionType == OptionType.BETWEEN
                        || optionType == OptionType.NOT_BETWEEN) {
                    multiFactor.setValue(query.value);
                    multiFactor.setToValue(otherValue);
                } else {
                    multiFactor.setValue(otherValue);
                    multiFactor.setToValue(query.value);
                }
                factors.add(multiFactor);
            } else {
                factors.add(factor);
                Factor otherFactor = builderFactor(other.factorType);
                otherFactor.setFieldName(key);
                otherFactor.setOptionType(other.optionType);
                otherFactor.setValue(other.value);
                factors.add(otherFactor);
            }
        });
        return factors;
    }

    private static FactorType getFactorType(Class<?> fieldClass) {
        if (fieldClass.isAssignableFrom(Enum.class)) {
            return FactorType.ENUM;
        } else if (fieldClass.isAssignableFrom(Number.class)) {
            return FactorType.NUMBER;
        } else if (fieldClass.isAssignableFrom(Boolean.class)) {
            return FactorType.BOOLEAN;
        } else if (fieldClass.isAssignableFrom(Collection.class)) {
            return FactorType.SET;
        } else if (fieldClass.isAssignableFrom(Temporal.class)) {
            return FactorType.DATE;
        } else {
            return FactorType.STRING;
        }
    }

    private static Factor builderFactor(FactorType factorType) {
        if (factorType == FactorType.NUMBER) {
            return new NumberFactor();
        } else if (factorType == FactorType.DATE) {
            return new DateFactor();
        } else if (factorType == FactorType.ENUM) {
            return new EnumFactor();
        } else if (factorType == FactorType.SET) {
            return new SetFactor();
        } else if (factorType == FactorType.BOOLEAN) {
            return new BooleanFactor();
        } else {
            return new StringFactor();
        }
    }

    private static <V> Object getFieldValue(Field field, V info) {
        String fieldName = field.getName();
        try {
            field.setAccessible(true);
            return field.get(info);
        } catch (IllegalAccessException e) {
            throw ExceptionUtils.mpe("The field can't access in the class, field:%s, class:%s",
                    fieldName, info.getClass());
        }
    }


    public static Method getEnumValueMethod(Class<?> cls) {
        if (IEnum.class.isAssignableFrom(cls)) {
            try {
                return cls.getMethod("getValue");
            } catch (NoSuchMethodException e) {
                throw ExceptionUtils.mpe(String.format("NoSuchMethod getValue() in class: %s.", cls.getName()));
            }
        } else {
            Field field = ReflectionKit.getUserAnnotationField(cls, EnumValue.class)
                    .orElseThrow(() -> ExceptionUtils.mpe(String.format("Could " +
                            "not find @EnumValue in class: %s.", cls.getName())));
            return ReflectionKit.getMethod(cls, field);
        }
    }

    private static boolean isBetweenSupported(OptionType optionType) {
        return optionType == OptionType.BETWEEN
                || optionType == OptionType.BETWEEN_END
                || optionType == OptionType.NOT_BETWEEN
                || optionType == OptionType.NOT_BETWEEN_END;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Query {
        private String fieldName;
        private Object value;
        private FactorType factorType;
        private OptionType optionType;
    }

}
