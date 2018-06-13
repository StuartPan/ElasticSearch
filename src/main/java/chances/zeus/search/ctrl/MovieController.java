package chances.zeus.search.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chances.zeus.search.ctrl.model.ListResponse;
import chances.zeus.search.ctrl.model.PageBean;
import chances.zeus.search.ctrl.model.QueryResponse;
import chances.zeus.search.ctrl.model.SearchResponseVO;

@RestController
public class MovieController {

	private static final String[] indexs = { "cms_movie", "cms_series", "cms_live_channel", "cms_album", "oms_subjects",
			"cms_category", "cms_material", "cms_favorite_entity", "cms_recycle_entity" };

	@Autowired
	private TransportClient client;

	// 查询
	@PostMapping("/query")
	public ResponseEntity query(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		List<Object> results = new ArrayList<>();
		HighlightBuilder hBuilder = new HighlightBuilder();
		hBuilder.field("name");
		hBuilder.field("actors");
		hBuilder.field("directors");
		hBuilder.field("viewPoint");
		hBuilder.field("introduction");
		hBuilder.preTags("<strong>");
		hBuilder.postTags("</strong>");
		for (String index : indexs) {
			try {
				QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "name", "actors", "directors",
						"viewPoint", "introduction");
				int from = (page - 1) * size;
				SearchRequestBuilder builder = this.client.prepareSearch(index).setTypes(index)
						.setSearchType(SearchType.QUERY_THEN_FETCH).setQuery(queryBuilder).highlighter(hBuilder).setFrom(from).setSize(size);
				SearchResponse response = builder.get();
				SearchHits hits = response.getHits();
				
				Long totalCounts = hits.getTotalHits();
				PageBean pageBean = new PageBean(totalCounts, size, page);
				
				// 解析高亮
				String[] needHighLightFields = {"name","actors","directors","viewPoint","introduction"};
				List<SearchResponseVO> responseVOs = new ArrayList<>();
				for (SearchHit hit : hits) {
					Map<String, String> highLights = new HashMap<>();
					Map<String, HighlightField> highlightFields = hit.getHighlightFields();
					Map<String, Object> source = hit.getSource(); 
					for (String needHighLightField : needHighLightFields) {
						HighlightField titleField = highlightFields.get(needHighLightField);
						if (titleField != null) {
							Text[] fragments = titleField.fragments();  
							if (fragments != null && fragments.length != 0){  
								StringBuilder name = new StringBuilder();  
								for (Text text : fragments) {  
									name.append(text);  
								}  
								highLights.put(needHighLightField, name.toString());
							}  
						}
					}
					// 设置id，code
					SearchResponseVO responseVO = new SearchResponseVO();
					responseVO.setItems(highLights);
					responseVO.setId(source.get("id").toString());
					responseVO.setCode(source.get("code").toString());
					responseVOs.add(responseVO);
				}
				results.add(new QueryResponse(index, responseVOs, pageBean));
			} catch (Exception e) {
				QueryResponse response = new QueryResponse();
				response.setErrorInfo(e.getMessage());
				response.setType(index);
				results.add(response);
			}
		}
		return new ResponseEntity(new ListResponse<>(results), HttpStatus.OK);
	}
	
	// 增加接口
//	@PostMapping("/add/{index}/{contentType}")
//	public ResponseEntity add(@PathVariable("index") String index, @PathVariable("contentType") String contentType,
//			@RequestBody Movie model) {
//		try {
//			XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("id", model.getId())
//					.field("code", model.getCode()).field("name", model.getName()).field("actors", model.getActors())
//					.field("directors", model.getDirectors()).field("viewPoint", model.getViewPoint())
//					.field("introduction", model.getIntroduction()).endObject();
//			IndexResponse response = this.client.prepareIndex(index, contentType).setSource(builder).get();
//			return new ResponseEntity(response.getId(), HttpStatus.OK);
//		} catch (IOException e) {
//			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
