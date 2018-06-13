package chances.zeus.search.ctrl.model;

import java.util.List;

/**
 * 
 * @author <a href="mailto:zhangxb@chances.com.cn">zhangxb</a>
 *
 * @param <T>
 */
public class ListResponse<T> extends BaseResponse {
    private List<T> result;

    public ListResponse() {
    }

    public ListResponse(List<T> list) {
        result = list;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

}
