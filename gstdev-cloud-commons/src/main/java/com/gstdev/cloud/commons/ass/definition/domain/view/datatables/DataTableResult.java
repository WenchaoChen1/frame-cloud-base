package com.gstdev.cloud.commons.ass.definition.domain.view.datatables;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * <p>Description: 返回给JQuery Datatables 组件使用的结果 </p>
 *
 * @author : cc
 * @date : 2019/11/24 15:48
 */
public class DataTableResult implements Serializable {

    private int pageNumber;
    private int pageSize;
    private String sEcho;
    private int iDisplayStart;
    private int iDisplayLength;
    private String jsonString;
    private long total;

    public DataTableResult(String sEcho, int iDisplayStart, int iDisplayLength, String jsonString) {
        this.sEcho = sEcho;
        this.iDisplayStart = iDisplayStart;
        this.iDisplayLength = iDisplayLength;
        this.pageNumber = this.iDisplayStart / this.iDisplayLength;
        this.pageSize = this.iDisplayLength;
        this.jsonString = jsonString;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pageNumber", pageNumber)
                .add("pageSize", pageSize)
                .add("sEcho", sEcho)
                .add("iDisplayStart", iDisplayStart)
                .add("iDisplayLength", iDisplayLength)
                .add("jsonString", jsonString)
                .add("total", total)
                .toString();
    }
}
