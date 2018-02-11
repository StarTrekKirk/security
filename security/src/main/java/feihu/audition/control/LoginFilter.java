package feihu.audition.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("loginfilter")
public class LoginFilter implements Filter {

	@Autowired
	private Login loginService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	private boolean isLogin(HttpServletRequest req) {
		return loginService.isLogin();
	}

	private boolean isStatic(HttpServletRequest req) {
		String uri = req.getRequestURI();
		return uri.indexOf(".css") != -1 || uri.indexOf(".js") != -1 || uri.indexOf(".html") != -1;
	}

	private boolean isLoginPage(HttpServletRequest req) {
		String uri = req.getRequestURI();
		return uri.indexOf("/html/login.html") != -1;
	}

	private boolean isLoginUri(HttpServletRequest req) {
		String uri = req.getRequestURI();
		return uri.indexOf(req.getContextPath() + "/login") != -1;
	}

	private String getReturnUrl(HttpServletRequest req) {
		String uri = req.getRequestURI();
		int index = uri.indexOf("returnUrl=");
		if (index == -1) {
			return null;
		}
		return uri.substring(index + "returnUrl=".length());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (isLogin(req)) {
			if (isLoginPage(req)) {
				String returnUrl = getReturnUrl(req);
				String url = res.encodeRedirectURL(req.getContextPath()
						+ (returnUrl != null ? returnUrl : "/home"));
				res.sendRedirect(url);
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			if (isStatic(req) || isLoginUri(req)) {
				chain.doFilter(request, response);
			}
			else {
				String uri = req.getRequestURI();
				String queryString = req.getQueryString();
				res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/html/login.html?returnUrl=" + uri
						+ (queryString == null ? "" : "?" + queryString)));
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
