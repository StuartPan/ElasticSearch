package chances.zeus.search.ctrl.model;

import java.util.Map;

public class SearchResponseVO {
	
	private String id;
	private String code;
	private Map<String, String> items;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, String> getItems() {
		return items;
	}
	public void setItems(Map<String, String> items) {
		this.items = items;
	}
}
