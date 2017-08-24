package com.jmarkstar.carlist_core.domain.model;

import java.util.HashMap;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public class PaginationMainTypesModel {

    private Integer page;
    private Integer pageSize;
    private Integer totalPageCount;
    private HashMap<String, String> mainTypes;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public HashMap<String, String> getMainTypes() {
        return mainTypes;
    }

    public void setMainTypes(HashMap<String, String> mainTypes) {
        this.mainTypes = mainTypes;
    }

    @Override
    public String toString() {
        return "PaginationMainTypesModel{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", totalPageCount=" + totalPageCount +
                ", mainTypes=" + mainTypes +
                '}';
    }
}
