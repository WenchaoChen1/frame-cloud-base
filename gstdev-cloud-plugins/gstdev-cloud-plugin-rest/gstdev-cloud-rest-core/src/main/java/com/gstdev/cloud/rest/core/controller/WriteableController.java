//package com.gstdev.cloud.rest.core.controller;
//
//import com.gstdev.cloud.commons.ass.definition.domain.Result;
//import com.gstdev.cloud.commons.ass.definition.domain.base.AbstractEntity;
//import com.gstdev.cloud.commons.ass.definition.domain.base.Entity;
//import com.gstdev.cloud.data.core.service.BaseService;
//import com.gstdev.cloud.data.core.service.WriteableService;
//
//import java.io.Serializable;
//
///**
// * <p> Description : 可写Controller </p>
// *
// * @author : cc
// * @date : 2020/4/30 11:00
// */
//public interface WriteableController<E extends Entity, ID extends Serializable, S extends BaseService<E, ID>> extends Controller<E, ID,S> {
//
//    /**
//     * 获取Service
//     *
//     * @return Service
//     */
//    S getService();
//
//    /**
//     * 保存或更新实体
//     *
//     * @param domain 实体参数
//     * @return 用Result包装的实体
//     */
//
//    default Result<E> saveOrUpdate(E domain) {
//        E savedDomain = getService().saveAndFlush(domain);
//        return result(savedDomain);
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param id 实体ID
//     * @return 用Result包装的信息
//     */
//    default Result<String> delete(ID id) {
//        Result<String> result = result(String.valueOf(id));
//        getService().deleteById(id);
//        return result;
//    }
//}
