package delprom.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleHeaderFilter extends OncePerRequestFilter {

	private static final String HEADER_NAME = "X-USER-ROLE";
	private static final String DEFAULT_ROLE = "ROLE_USER";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getAuthorities() != null
				&& !authentication.getAuthorities().isEmpty()) {

			String role = authentication.getAuthorities().iterator().next().getAuthority();
			response.setHeader(HEADER_NAME, role);

		} else {

			response.setHeader(HEADER_NAME, DEFAULT_ROLE);

		}

		filterChain.doFilter(request, response);

	}

}
