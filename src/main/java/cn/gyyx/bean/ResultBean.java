package cn.gyyx.bean;

public class ResultBean<T> {
	private Boolean isSuccess;
	private String message;
	private  T data;
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ResultBean(Boolean isSuccess, String message, T data) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
		this.data = data;
	}
	
	
}
