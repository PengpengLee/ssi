package cn.itcast.ssi.common.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <p>
 * 访问日志拦截器
 * </p>
 * 性能要求高的请求中加入此拦截器，方便性能调优
 * 
 * @author lipp
 * 
 */
public class ActionLoggingInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	private final Logger log = Logger.getLogger("ActionProfilerLog");
	private final String FINISH_MESSAGE = "Finishing for action:";
	private final String START_MESSAGE = "Starting for action:";

	private long startTime;

	private void after(ActionInvocation invocation, String result)
			throws Exception {
		if (log.isInfoEnabled()) {
			long usedTime = System.currentTimeMillis() - startTime;

			StringBuffer message = new StringBuffer(FINISH_MESSAGE);
			String namespace = invocation.getProxy().getNamespace();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				message.append(namespace);
				if (!namespace.trim().endsWith("/")) {
					message.append("/");
				}
			}

			message.append(invocation.getProxy().getActionName());

			message.append(" used:");
			message.append(usedTime);
			// 写result日志־
			log.info("Action result:" + result);
			// 写结束日志
			log.info(message.toString());
		}
	}

	private void before(ActionInvocation invocation) throws Exception {
		if (log.isInfoEnabled()) {
			startTime = System.currentTimeMillis();

			StringBuffer message = new StringBuffer(START_MESSAGE);
			String namespace = invocation.getProxy().getNamespace();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				message.append(namespace);
				if (!namespace.trim().endsWith("/")) {
					message.append("/");
				}
			}

			message.append(invocation.getProxy().getActionName());
			// 写开始日志
			log.info(message.toString());
			// 写输入参数日志
			log.info(invocation.getAction());
		}
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		before(arg0);
		String result = arg0.invoke();
		after(arg0, result);
		return result;
	}
}
