package com.gstdev.cloud.data.core.repository;

import com.gstdev.cloud.commons.ass.definition.domain.base.Entity;
import com.gstdev.cloud.data.core.entity.BaseTreeEntity;
import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface BaseTreeRepository<E extends BaseTreeEntityINT, ID extends Serializable> extends BaseRepository<E,ID>{
  List<E> findByParentId(ID parentId);
}
