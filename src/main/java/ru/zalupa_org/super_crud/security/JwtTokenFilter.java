package ru.zalupa_org.super_crud.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Component
public class JwtTokenFilter implements Filter {
    private final JwtTokenProvider provider;

    public JwtTokenFilter(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Optional<String> optionalToken = Optional.ofNullable(provider.resolveToken((HttpServletRequest) servletRequest));
        try {
            if (optionalToken.isPresent() && !optionalToken.get().isEmpty() && provider.validateToken(optionalToken.get())) {
                Authentication authentication = provider.getAuthentication(optionalToken.get());
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e){
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
