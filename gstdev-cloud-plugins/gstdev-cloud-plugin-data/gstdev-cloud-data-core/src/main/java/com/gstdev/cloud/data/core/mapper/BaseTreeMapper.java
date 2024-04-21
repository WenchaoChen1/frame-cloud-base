package com.gstdev.cloud.data.core.mapper;

import com.gstdev.cloud.data.core.entity.BaseTreeEntityINT;
import com.gstdev.cloud.data.core.pojo.BaseTreeDto;
import com.gstdev.cloud.data.core.entity.BaseTreeEntity;
import com.gstdev.cloud.data.core.pojo.BaseTreeInsertInput;
import com.gstdev.cloud.data.core.pojo.BaseTreeUpdateInput;

public interface BaseTreeMapper<E extends BaseTreeEntityINT
    , D extends BaseTreeDto
    , II extends BaseTreeInsertInput
    , UI extends BaseTreeUpdateInput> extends BaseMapper<E, D, II, UI> {

}
