package com.blog.init.vo;

/**
 * 
 * @author wangzn
 * @date 2019年4月21日
 */
public class Response {
	private Boolean success;//结果成功否
	private String message;//结果返回信息
	private Object body;//返回数据
	
	
	public Response(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	
	
	public Response(Boolean success, String message, Object body) {
		this.success = success;
		this.message = message;
		this.body = body;
	}

	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	
}
