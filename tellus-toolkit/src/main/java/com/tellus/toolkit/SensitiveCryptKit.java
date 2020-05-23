package com.tellus.toolkit;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.DES;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.tellus.support.annotation.ISensitive;
import com.tellus.support.annotation.Nullable;
import com.tellus.support.enums.SensitiveTypeEnum;
import com.tellus.support.interfaces.ISensitiveType;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通用敏感数据加密工具类
 *
 * @author Roy
 * @date 2020/5/24 0:43
 * @see com.tellus.support.annotation.ISensitive
 */
@Data
public class SensitiveCryptKit {

    /**
     * 原始秘钥
     */
    private String originalSecret;
    /**
     * 敏感字段
     */
    private Set<String> sensitiveFields;

    public SensitiveCryptKit(String originalSecret) {
        this.originalSecret = originalSecret;
    }

    public SensitiveCryptKit(String originalSecret, Set<String> sensitiveFields) {
        this.originalSecret = originalSecret;
        this.sensitiveFields = sensitiveFields;
    }

    // ~ Enhanced decryption Methods
    // ============================================================================================

    public Map<String, Object> encryption(Map<String, Object> src) {
        return this.encryption(src, null, null);
    }

    public Map<String, Object> encryption(Map<String, Object> src, ISensitiveType sensitiveType) {
        return this.encryption(src, sensitiveType, null);
    }

    public Map<String, Object> encryption(Map<String, Object> src, ISensitiveType sensitiveType, String factor) {
        src.forEach((key, value) -> {
            if (sensitiveFields.contains(key)) {
                src.put(key, this.encryption(value.toString(), sensitiveType, factor));
            }
        });
        return src;
    }

    public <V> List<V> encryption(List<V> src) {
        return src.stream().map(this::encryption).collect(Collectors.toList());
    }

    public <V> V encryption(V src) {
        return this.encryption(src, null, null);
    }

    public <V> V encryption(V src, ISensitiveType sensitiveType) {
        return this.encryption(src, sensitiveType, null);
    }

    public <V> V encryption(V src, @Nullable ISensitiveType sensitiveType, @Nullable String factor) {
        Preconditions.checkNotNull(src, "[src] must not be null");

        for (Field field : src.getClass().getDeclaredFields()) {
            ISensitive sensitive = field.getAnnotation(ISensitive.class);

            //  无注解, 或者不在特性的敏感字段集合中
            if (null == sensitive && !sensitiveFields.contains(field.getName())) {
                continue;
            }

            try {
                field.setAccessible(true);
                String value = (String) field.get(src);
                field.set(src, this.decryption(value, sensitiveType, factor));
            } catch (IllegalAccessException e) {
                throw ExceptionUtils.mpe("Encryption exception, fieldName:%s, %s",
                        field.getName(), e.getMessage(), e);
            }
        }
        return src;
    }

    public String encryption(String value) {
        return this.encryption(value, null, null);
    }

    public String encryption(String value, ISensitiveType sensitiveType) {
        return this.encryption(value, sensitiveType, null);
    }

    public String encryption(String value, @Nullable ISensitiveType sensitiveType, @Nullable String factor) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        if (null == sensitiveType) {
            sensitiveType = SensitiveTypeEnum.GENERAL;
        }
        return this.encrypt(value, builderSecret(sensitiveType, factor));
    }

    // ~ Enhanced decryption Methods
    // ============================================================================================

    public Map<String, Object> decryption(Map<String, Object> src) {
        return this.decryption(src, null, null);
    }

    public Map<String, Object> decryption(Map<String, Object> src, ISensitiveType sensitiveType) {
        return this.decryption(src, sensitiveType, null);
    }

    public Map<String, Object> decryption(Map<String, Object> src, ISensitiveType sensitiveType, String factor) {
        src.forEach((key, value) -> {
            if (sensitiveFields.contains(key)) {
                src.put(key, this.decryption(value.toString(), sensitiveType, factor));
            }
        });
        return src;
    }

    public <V> List<V> decryption(List<V> src) {
        return src.stream().map(this::decryption).collect(Collectors.toList());
    }

    public <V> V decryption(V src) {
        return this.decryption(src, null, null);
    }

    public <V> V decryption(V src, ISensitiveType sensitiveType) {
        return this.decryption(src, sensitiveType, null);
    }

    public <V> V decryption(V src, @Nullable ISensitiveType sensitiveType, @Nullable String factor) {
        Preconditions.checkNotNull(src, "[src] must not be null");

        for (Field field : src.getClass().getDeclaredFields()) {
            ISensitive sensitive = field.getAnnotation(ISensitive.class);

            //  无注解, 或者不在特性的敏感字段集合中
            if (null == sensitive && !sensitiveFields.contains(field.getName())) {
                continue;
            }

            try {
                field.setAccessible(true);
                String value = (String) field.get(src);
                field.set(src, this.decryption(value, sensitiveType, factor));

            } catch (IllegalAccessException e) {
                throw ExceptionUtils.mpe("Decryption exception, fieldName:%s, %s",
                        field.getName(), e.getMessage(), e);
            }
        }
        return src;
    }

    public String decryption(String cipherText) {
        return this.decryption(cipherText, null, null);
    }

    public String decryption(String cipherText, ISensitiveType sensitiveType) {
        return this.decryption(cipherText, sensitiveType, null);
    }

    public String decryption(String cipherText, @Nullable ISensitiveType sensitiveType, @Nullable String factor) {
        if (Strings.isNullOrEmpty(cipherText)) {
            return null;
        }
        if (null == sensitiveType) {
            sensitiveType = SensitiveTypeEnum.GENERAL;
        }
        return this.decrypt(cipherText, builderSecret(sensitiveType, factor));
    }


    // ~ Basic Methods
    // ============================================================================================

    public String encrypt(String value) {
        return this.encrypt(value, builderSecret());
    }

    public String decrypt(String value) {
        return this.decrypt(value, builderSecret());
    }

    public String encrypt(String value, String secret) {
        return new DES(secret.getBytes(CharsetUtil.CHARSET_UTF_8)).encryptBase64(value);
    }

    public String decrypt(String value, String secret) {
        return new DES(secret.getBytes(CharsetUtil.CHARSET_UTF_8)).decryptStr(value);
    }

    /**
     * 生成新的KEY: 默认生成 GENERAL
     *
     * @return 新的秘钥串
     */
    public String builderSecret() {
        return this.builderSecret(SensitiveTypeEnum.GENERAL, null);
    }

    /**
     * 生成新的KEY
     *
     * @param type   敏感值类型
     * @param factor 变量因子
     * @return 新的秘钥串
     */
    public String builderSecret(ISensitiveType type, @Nullable String factor) {
        if (null == originalSecret) {
            throw ExceptionUtils.mpe("SensitiveCryptKit parameter[secret] must not be empty.");
        }

        int reLength = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(type.getKeyPrefix());

        reLength += type.getKeyPrefix().length();
        if (null != factor) {
            sb.append(factor);
            reLength += factor.length();
        }
        sb.append(originalSecret, reLength - 1, originalSecret.length() - 1);

        return sb.toString();
    }
}
