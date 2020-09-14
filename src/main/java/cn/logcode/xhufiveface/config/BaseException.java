package cn.logcode.xhufiveface.config;


import cn.logcode.xhufiveface.result.IErrorCode;

public class BaseException extends RuntimeException {

	public static final long serialVersionUID = 1L;
	
	public BaseException() {
		super();
		this.code = 500;
		this.msg = super.getMessage();
	}
	
	public BaseException(String msg) {
		super(msg);
		this.code = 500;
		this.msg = msg;
	}

	public BaseException(int code) {
		super();
		this.code = code;
		this.msg = super.getMessage();
	}
	
	public BaseException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	public BaseException(IErrorCode errorCode) {
		this.code = (int) errorCode.getCode();
		this.msg = errorCode.getMessage();
	}


	public int code;
	public String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "BaseException{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				'}';
	}


}
