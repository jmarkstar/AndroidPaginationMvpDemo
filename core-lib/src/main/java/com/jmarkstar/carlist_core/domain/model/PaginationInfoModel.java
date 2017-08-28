package com.jmarkstar.carlist_core.domain.model;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public class PaginationInfoModel {

    private int page;
    private int totalPageCount;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    @Override
    public String toString() {
        return "PaginationInfoModel{" +
                "page=" + page +
                ", totalPageCount=" + totalPageCount +
                '}';
    }
}
