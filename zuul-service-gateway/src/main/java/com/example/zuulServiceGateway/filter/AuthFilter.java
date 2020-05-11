package com.example.zuulServiceGateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterUtils.AUTH_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Object serviceId = requestContext.get("serviceId");
        if (serviceId != null && FilterUtils.FLIGHT_SCHEDULE_SERVICE_NAME.equals(serviceId.toString())) {
            String token = requestContext.getRequest().getParameter(FilterUtils.FLIGHT_SCHEDULE_AUTH_TOKEN_KEY);
            if (!FilterUtils.FLIGHT_SCHEDULE_AUTH_TOKEN_VALUE.equals(token)) {
                requestContext.setResponseStatusCode(401);
                requestContext.setResponseBody("The request is not auth.");
                requestContext.getResponse().setContentType("application/json");
                requestContext.setSendZuulResponse(false);
            }
        }
        return null;
    }
}
