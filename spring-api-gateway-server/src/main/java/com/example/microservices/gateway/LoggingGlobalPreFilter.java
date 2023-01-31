package com.example.microservices.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggingGlobalPreFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingGlobalPreFilter.class);
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request =
                RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}",
                request, request.getRequestURI());
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}
