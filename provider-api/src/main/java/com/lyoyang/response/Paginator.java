package com.lyoyang.response;

import lombok.Data;

import java.io.Serializable;

/**
 * xxxxxxxxxxxxxx
 *
 * @author: xxxxx
 * @create: 2019/7/5-14:11
 *
 */
@Data
public class Paginator implements Serializable {

    /**
     * 默认页码
     */
    public static final int DEFAULT_PAGE = 1;

    /**
     * 每页默认条数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 页码
     */
    private int pageNumber;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 总页数
     */
    private long pageNum;
    /**
     * 总记录数
     */
    private long totalCount;

    public Paginator(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public void computePageNum(Paginator paginator) {
        Long nowTotalCount = paginator.getTotalCount();
        if (nowTotalCount != 0) {
            // 计算总页数
            this.pageNum = nowTotalCount / paginator.getPageSize();
            // 总条数除不尽每页显示条数时，总页数加一
            if (nowTotalCount % paginator.getPageSize() != 0) {
                this.pageNum++;
            }
        }
    }
}
