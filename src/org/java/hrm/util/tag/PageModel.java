package org.java.hrm.util.tag;

import org.java.hrm.util.common.HrmConstants;

public class PageModel {
    private int recordCount; //总记录数
    private int pageIndex;   //当前页码
    private int pageSize = HrmConstants.PAGE_DEFAULT_SIZE;
    private int totalSize;   //总页数

    public int getRecordCount() {
        this.recordCount = this.recordCount <= 0 ? 0 : this.recordCount;
        return this.recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        this.pageIndex = this.pageIndex <= 0 ? 1 : this.pageIndex;
        this.pageIndex = this.pageIndex <= this.getTotalSize() ? this.pageIndex : this.getTotalSize();
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex >= 0 ? pageIndex : 0;
    }

    public int getTotalSize() {
        if (this.getRecordCount() <= 0) {
            this.totalSize = 0;
        }else {
            this.totalSize = (this.recordCount - 1) / this.pageSize + 1;
        }
        return totalSize;
    }

    public int getPageSize() {
        this.pageSize = this.pageSize <= HrmConstants.PAGE_DEFAULT_SIZE ? this.pageSize : HrmConstants.PAGE_DEFAULT_SIZE;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstLimitParam() {
        return (this.getPageIndex() - 1) <= 0 ? 0 : (this.getPageIndex() - 1) * this.getPageSize();
    }
}
