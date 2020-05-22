package com.tellus.config.dozer;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dozer 深度复制工具
 *
 * @author Roy
 * @date 2020/5/21 12:53
 */
public class DozerGenerator implements IGenerator {

    private final Mapper dozerMapper = DozerBeanMapperBuilder.buildDefault();

    @Override
    public <T, S> T convert(S src, Class<T> clz) {
        if (null == src) {
            return null;
        }
        return this.dozerMapper.map(src, clz);
    }

    @Override
    public <T, S> List<T> convert(List<S> src, Class<T> clz) {
        if (null == src) {
            return null;
        }

        List<T> list = new ArrayList<>();
        for (S s : src) {
            list.add(this.dozerMapper.map(s, clz));
        }
        return list;
    }

    @Override
    public <T, S> Set<T> convert(Set<S> src, Class<T> clz) {
        if (null == src) {
            return null;
        }

        Set<T> set = new HashSet<>();
        for (S s : src) {
            set.add(this.dozerMapper.map(s, clz));
        }
        return set;
    }

    @Override
    public <T, S> T[] convert(S[] src, Class<T> clz) {
        if (null == src) {
            return null;
        }

        @SuppressWarnings("unchecked") T[] arr = (T[]) Array.newInstance(clz, src.length);
        for (int i = 0; i < src.length; i++) {
            arr[i] = this.dozerMapper.map(src[i], clz);
        }

        return arr;
    }
}
