package com.example.zuulServiceGateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.io.IOException;

public class RouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterUtils.ROUTER_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("Router Filter is invoked");
        RequestContext requestContext = RequestContext.getCurrentContext();
        Object serviceId = requestContext.get("serviceId");
        if (serviceId != null && FilterUtils.CURRENCY_CONVERSION_SERVICE_NAME.equals(serviceId)) {
            if (FilterUtils.useNewRoute()) {
                String oldEndpoint = requestContext.getRequest().getRequestURI();
                String newEndpoint = "/currency-conversion-service-beta";

                int index = oldEndpoint.indexOf("css");
                String strippedRoute = oldEndpoint.substring((index == -1 ? 1 : index) + "ccs".length());
                String route = String.format("%s%s", newEndpoint, strippedRoute);
                System.out.println("New route ->> " + route);
                //disable SimpleHostRouting FIlter
                requestContext.setRouteHost(null);
                //disable RibbonRouting filter
                requestContext.remove(serviceId);
                try {
                    RequestContext.getCurrentContext().getResponse().sendRedirect(route);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
}
