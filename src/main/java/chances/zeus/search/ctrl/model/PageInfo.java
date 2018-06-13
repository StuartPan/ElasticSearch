package chances.zeus.search.ctrl.model;

public class PageInfo {
    
	private PageBean pageBean;
	
	public PageInfo(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
//	@JsonView(Profile.PublicView.class)
	public int getRecordCount() {
		return this.pageBean.getRecordCount();
	}
	
	public int getPageCount() {
		return this.pageBean.getPageCount();
	}
	
	public int getPageSize() {
		return this.pageBean.getPageSize();
	}
	
	public int getCurrentPage() {
		return this.pageBean.getCurrentPage();
	}

    /**
     * @return
     * @see chances.zeus.cms.ctrl.model.epg.data.PageBean#getNextPageNo()
     */
    public int getNextPageNo() {
        return pageBean.getNextPageNo();
    }

    /**
     * @return
     * @see chances.zeus.cms.ctrl.model.epg.data.PageBean#getPrivPageNo()
     */
    public int getPrivPageNo() {
        return pageBean.getPrivPageNo();
    }
	
	
	
}
