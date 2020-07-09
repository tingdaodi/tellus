package com.tellus.permission.cloud.support;

/**
 * Service 通用接口
 * <p>
 * V - Result VO
 * S - Save VO
 * R - Retrieve VO
 * U - Update VO
 *
 * @author Roy
 * @date 2020/7/9 19:10
 */
public interface IBasicService<V, S, R, U> extends ISaveService<S>, IRetrieveService<V, R>, IUpdateService<U> {
}
