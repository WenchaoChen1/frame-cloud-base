package com.gstdev.cloud.data.core.entity;

public interface BaseTreeEntityINT<ID> extends BasePOJOEntityINT<ID> {
    ID getParentId();
}
