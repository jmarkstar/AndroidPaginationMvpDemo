package com.jmarkstar.carlist_core.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmarkstar on 24/08/2017.
 */
public class PaginationItemsModel {

    private Integer page;
    private Integer pageSize;
    private Integer totalPageCount;
    private List<ItemModel> items;

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

    public List<ItemModel> getItems() {
        if(items== null) items = new ArrayList<>();
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PaginationItemsModel{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", totalPageCount=" + totalPageCount +
                ", items size=" + getItems().size() +
                '}';
    }
}
