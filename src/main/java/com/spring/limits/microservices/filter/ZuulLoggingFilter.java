package com.spring.limits.microservices.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * We have to filter the request going from Zuul API Gateway.
 * @author Champ
 *
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private static final Logger LOGGER=LoggerFactory.getLogger(ZuulLoggingFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		LOGGER.info("request -> {} request URI -> {}", request, request.getRequestURI());
		return null;
	}

	/**
	 * This indicates when should filtering happen.
	 * pre: Before execution of request.
	 * post: After the request is executed.
	 * error: We want to filter only error request.
	 */
	@Override
	public String filterType() {
		return "pre"; //for all the requests.
	}


	@Override
	public int filterOrder() {
		return 0;
	}

}
