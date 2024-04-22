package com.gstdev.cloud.data.core.repository;

import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;

import java.io.Serializable;
import java.util.List;

public interface BaseTreeRepository<E extends BaseTreeEntityINT<ID>, ID extends Serializable> extends BaseRepository<E,ID>{
  List<E> findByParentId(ID parentId);
}
