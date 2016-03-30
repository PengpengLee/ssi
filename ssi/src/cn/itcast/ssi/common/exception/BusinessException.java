package cn.itcast.ssi.common.exception;

import org.apache.log4j.Logger;

public class BusinessException extends Exception {

	private Logger log = Logger.getLogger("BusinessExceptionLog");

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
		log.error(message);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
		log.error(throwable);
	}

	public BusinessException(Throwable throwable, String classname,
			String methodname) {
		super(throwable);
		log.error(classname + "\t" + methodname + "\r\n" + throwable, throwable);
	}

	public BusinessException(Throwable throwable, String classname,
			String methodname, String email, String date) {
		super(throwable);
		log.error(classname + "\t" + methodname + "\r\n" + throwable
				+ "\r\nTarget Email:" + email + "\t" + date, throwable);
	}

}
