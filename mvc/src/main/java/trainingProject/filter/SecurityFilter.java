package trainingProject.filter;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import trainingProject.model.User;
import trainingProject.service.JWTService;
import trainingProject.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static String AUTH_URI = "/auth";
    private static String SIGNUP_URI = "/auth/signup";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //extract Authorization header
    //remove Bearer to get token
    //decrypt token to get claim
    //verify username information in the database from claim
    //doFilter dispatch to controller
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /* This way of spring bean autowire to servlet filter is not recommended, because it's needed for each filter
        if (userService == null) {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletRequest.getServletContext());
        } */
        int statusCode = authorization((HttpServletRequest) servletRequest);
        if (statusCode == HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(servletRequest, servletResponse);
        else ((HttpServletResponse)servletResponse).sendError(statusCode);
    }

    @Override
    public void destroy() {

    }

    private int authorization(HttpServletRequest req) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        String verb = req.getMethod();
        if (uri.equalsIgnoreCase(AUTH_URI)) return HttpServletResponse.SC_ACCEPTED;
        if (uri.equalsIgnoreCase(SIGNUP_URI)) return HttpServletResponse.SC_ACCEPTED;

        try {
            String token = req.getHeader("Authorization").replaceAll("^(.*?) ", "");
            if (token == null || token.isEmpty()) return statusCode;
            Claims claims = jwtService.decryptJwtToken(token);
            if (claims.getId() != null) {
                User u = userService.getBy(Long.valueOf(claims.getId()));
                if (u == null) return statusCode;
               // statusCode = HttpServletResponse.SC_ACCEPTED;
            }

            String allowedResources = "/";
            switch(verb) {
                case "GET"    : allowedResources = (String)claims.get("allowedReadResources");   break;
                case "POST"   : allowedResources = (String)claims.get("allowedCreateResources"); break;
                case "PUT"    : allowedResources = (String)claims.get("allowedUpdateResources"); break;
                case "DELETE" : allowedResources = (String)claims.get("allowedDeleteResources"); break;
            }

            for (String s : allowedResources.split(",")) {
                if (uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }

        }
        catch (Exception e) {
            logger.error("can't verify the token", e);
        }
        return statusCode;
    }
}
