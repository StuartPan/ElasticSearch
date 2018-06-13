package chances.zeus.search.ctrl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author <a href="mailto:zhangxb@chances.com.cn">zhangxb</a>
 *
 */
public class BaseResponse {

    public static final int SUCCESS = 200;
    public static final int ERROR = 999;
    private int code = SUCCESS;
    private String msg = "ok";

    public BaseResponse() {

    }

    public BaseResponse(int code) {
        this.code = code;
        if (code == SUCCESS) {
            this.msg = "ok";
        } else {
            this.msg = null;
        }
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setErrorInfo(String msg, int errorCode) {
        this.msg = msg;
        this.code = errorCode;
    }

    public void setErrorInfo(String msg) {
        setErrorInfo(msg, ERROR);
    }


    @JsonIgnore
    public boolean isError() {
        return code != SUCCESS;
    }
    @JsonIgnore
    public boolean isSuccess() {
        return code == SUCCESS;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BaseResponse [code=" + code + ", msg=" + msg + "]";
    }

}
