package cn.gyyx.bean;

public class ResultBeanNet<T> {

	private Boolean is_success;

	private String error_message;

	private String error;

	private T data;

	public Boolean getIs_success() {
		return is_success;
	}

	public void setIs_success(Boolean is_success) {
		this.is_success = is_success;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultBeanNet(Boolean is_success, String error_message, String error,
            T data) {
        super();
        this.is_success = is_success;
        this.error_message = error_message;
        this.error = error;
        this.data = data;
    }

	public static <T> ResultBeanNet<T> fail(String message, T data) {
		return new ResultBeanNet<>(false, message, "fail", data);
	}

	public static <T> ResultBeanNet<T> ok(String message, T data) {
		return new ResultBeanNet<>(true, message, "success", data);
	}

	public static <T> ResultBeanNet<T> ok(T data) {
		return new ResultBeanNet<>(true, "", "success", data);
	}

}
