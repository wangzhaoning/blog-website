package com.blog.init.vo;

/**
 * 
 * @author wangzn
 * @date 2019��4��21��
 */
public class Response {
	private Boolean success;//����ɹ���
	private String message;//���������Ϣ
	private Object body;//��������
	
	
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
