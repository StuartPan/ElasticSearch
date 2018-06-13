package chances.zeus.search.ctrl.model;

public class PageBean implements java.io.Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -4669061527236535414L;

//    @JsonView(Profile.PublicView.class)
    private int pageCount; // 总页数
    
    private int pageSize = 20; // 页大小
    
    private int lastPageSize; // 最后一页的记录数
    
    private long recordCount; // 记录总数
    
    private int currentPage = 1;// 当前页
    
    private int skip;

    /**
     * Creates a new PageBean object.
     */
    public PageBean() {
        super();
    }

    /**
     * Creates a new PageBean object.
     * 
     * @param pageSize
     */
    public PageBean(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Creates a new PageBean object.
     * 
     * @param recordCount
     * @param pageSize
     */
    public PageBean(long recordCount, int pageSize, int currentPage) {
        this.recordCount = recordCount > 0 ? recordCount : 0;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        calculate();
    }

    /**
     * 计算总页�?.
     */
    public void calculate() {
        if (this.pageSize == 0) {
            this.pageSize = 1;
        }
        this.pageCount = (int) recordCount / pageSize;

        this.lastPageSize = (int) this.recordCount % this.pageSize;

        if (this.lastPageSize == 0 && pageCount != 0) {
            this.lastPageSize = this.pageSize;
        } else {
            if (this.recordCount == 0) {
                this.pageCount = 0;
            } else {
                this.pageCount++;
            }
        }

        // 如果当前页超出�?�页数时，修正当前页
        if (this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }
    }

    /**
     * 取得当前页页�?.
     * 
     * @return
     */
    public int getCurrentPage() {
        return this.currentPage;
    }

    /**
     * 取得当前页页�?.
     * 
     * @return
     */
    public int getPageNo() {
        return this.currentPage;
    }

    /**
     * 取得当前页第�?条数据记录索�?.
     * 
     * @return
     */
    public int getCurrentPageFirstRecord() {
        return (this.currentPage - 1) * this.pageSize;
    }

    /**
     * 取得当前页记录�?�数.
     * 
     * @return
     */
    public int getCurrentPageSize() {
        if (this.pageCount == this.currentPage) {
            return this.getLastPageSize();
        } else {
            return this.pageSize;
        }
    }

    /**
     * 取得当前页第�?条数据索引和�?后一条数据索�?.
     * 
     * @return
     */
    public int[] getCurrentScope() {
        if (pageCount == 0) {
            return new int[] { 0, 0 };
        }

        int[] result = new int[2];

        if (this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }

        result[0] = (this.currentPage - 1) * pageSize;
        result[1] = result[0] + pageSize;

        if (this.currentPage == this.pageCount) {
            result[1] = result[0] + this.lastPageSize;
        }

        return result;
    }

    /**
     * 取得记录总数.
     * 
     * @return PageBean.recordCount
     */
    public int getIntegerRecordCount() {
        return (int) this.recordCount;
    }

    /**
     * 取得�?后一页的记录总数.
     */
    public int getLastPageSize() {
        return this.lastPageSize;
    }

    /**
     * 取得下一页页�?.
     * 
     * @return
     */
    public int getNextPageNo() {
        int result = this.currentPage + 1;

        if (result > this.pageCount) {
            result = this.pageCount;
        }

        return result;
    }

    /**
     * 取得总页�?.
     * 
     * @return
     */
    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * 取得页面大小.
     * 
     * @return
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 取得上一页页�?.
     * 
     * @return
     */
    public int getPrivPageNo() {
        int result = this.currentPage - 1;

        if (result < 1) {
            result = 1;
        }

        return result;
    }

    /**
     * 得到总共有多少条记录.
     * 
     * @return
     */
    public int getRecordCount() {
        return ((pageCount - 1) * pageSize) + lastPageSize;
    }

    /**
     * 是否是第�?�?.
     * 
     * @return
     */
    public boolean isFirst() {
        return this.currentPage == 1;
    }

    /**
     * 是否是最后一�?.
     * 
     * @return
     */
    public boolean isLast() {
        return this.pageCount <= this.currentPage;
    }

    /**
     * 是否还有下一�?.
     * 
     * @return
     */
    public boolean isNext() {
        return this.pageCount > this.currentPage;
    }

    /**
     * 是否还有上一�?.
     * 
     * @return
     */
    public boolean isPriv() {
        return this.currentPage > 1;
    }

    /**
     * 
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @param lastPageSize
     */
    public void setLastPageSize(int lastPageSize) {
        this.lastPageSize = lastPageSize;
    }

    /**
     * 设置�?.
     * 
     * @param recordCount
     * @param pageSize
     */
    public void setPage(long recordCount, int pageSize) {
        this.recordCount = recordCount;
        this.pageSize = pageSize;
        calculate();
    }

    /**
     * 
     * @param pageCount
     */
    protected void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 
     * @param recordCount
     */
    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;

        if (this.pageSize != 0) {
            this.calculate();
        }
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getSkip() {
        return skip;
    }

}
