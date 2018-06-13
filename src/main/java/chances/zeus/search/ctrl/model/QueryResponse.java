package chances.zeus.search.ctrl.model;

import java.util.List;

public class QueryResponse extends BaseResponse {
	
	private String type;
	private List<SearchResponseVO> items;
	private PageInfo pageInfo;
	
	public QueryResponse() {
		
	}
	
	public QueryResponse(String type, List<SearchResponseVO> items, PageBean pageBean) {
		this();
		this.type = type;
        this.items = items;
        if (pageBean != null) {
            this.pageInfo = new PageInfo(pageBean);
        }
	}
	
	public List<SearchResponseVO> getItems() {
		return items;
	}
	public void setItems(List<SearchResponseVO> items) {
		this.items = items;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
