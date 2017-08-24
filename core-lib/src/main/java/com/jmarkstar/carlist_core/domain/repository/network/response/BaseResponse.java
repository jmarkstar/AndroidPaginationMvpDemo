package com.jmarkstar.carlist_core.domain.repository.network.response;

/** Generic base response for any lists with pagination.
 * Created by jmarkstar on 23/08/2017.
 */
public class BaseResponse<M> {
    private Integer page;
    private Integer pageSize;
    private Integer totalPageCount;
    private M wkda;

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

    public M getWkda() {
        return wkda;
    }

    public void setWkda(M wkda) {
        this.wkda = wkda;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", totalPageCount=" + totalPageCount +
                ", wkda=" + wkda +
                '}';
    }
}
