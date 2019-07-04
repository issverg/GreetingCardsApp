package il.co.sela.targil.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class TokenAuthenticationFilter implements Filter {
	TokenAuthenticationManager manager;
	
	public TokenAuthenticationFilter(TokenAuthenticationManager tokenManager) {
		super();
		this.manager = tokenManager;
	}	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		TokenAuthentication tAuthentication = new TokenAuthentication();
		
		String token = httpServletRequest.getHeader("token");
		
		if (token != null) {
			tAuthentication.setToken(token);
		}
		
		Authentication authentication = manager.authenticate(tAuthentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(httpServletRequest, httpServletResponse);
	}
}