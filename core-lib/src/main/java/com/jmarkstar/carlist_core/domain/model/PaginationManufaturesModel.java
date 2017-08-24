package com.jmarkstar.carlist_core.domain.model;

import java.util.HashMap;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public class PaginationManufaturesModel {

    private Integer page;
    private Integer pageSize;
    private Integer totalPageCount;
    private HashMap<String, String> manufactures;

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

    public HashMap<String, String> getManufactures() {
        return manufactures;
    }

    public void setManufactures(HashMap<String, String> manufactures) {
        this.manufactures = manufactures;
    }

    @Override
    public String toString() {
        return "PaginationManufaturesModel{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", totalPageCount=" + totalPageCount +
                ", manufactures=" + manufactures +
                '}';
    }
}
