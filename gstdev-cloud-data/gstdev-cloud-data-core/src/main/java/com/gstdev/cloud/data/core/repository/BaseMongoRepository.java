package com.gstdev.cloud.data.core.repository;

import com.gstdev.cloud.data.core.entity.BaseMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * <p>Description: Spring Data Mongo 基础 Repository </p>
 *
 * @author : cc
 * @date : 2023/2/26 17:07
 */
public interface BaseMongoRepository<E extends BaseMongoEntity, ID extends Serializable> extends MongoRepository<E, ID> {
}
