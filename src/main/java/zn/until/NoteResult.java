package zn.until;

import java.io.Serializable;

/**
 * 作为统一的返回信息
 *
 */
public class NoteResult implements Serializable{
	private int status;//状态
	private String msg;//提示消息
	private Object data;//返回的数据
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
