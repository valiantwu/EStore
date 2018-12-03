package org.woo.dataentity.page;

import java.io.Serializable;

public class PageParam implements Serializable {
    private int pageNum; // 当前页数
    private int numPerPage; // 每页记录数

    public PageParam() {
    }

    public PageParam(int pageNum, int numPerPage) {
        super();
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public PageParam setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public PageParam setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
        return this;
    }
}
