package bootcamp.authenticationservice.infrastructure.configuration.security.filter;

import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.exception.MalformJwtException;
import bootcamp.authenticationservice.infrastructure.configuration.security.JwtService;
import bootcamp.authenticationservice.until.JwtConst;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final IUserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, MalformJwtException {
        String authorizationHeader = request.getHeader(JwtConst.HEADER_STRING);
        if (authorizationHeader == null || !authorizationHeader.startsWith(JwtConst.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String jwt = authorizationHeader.split(JwtConst.SPLITERSTRING)[1];
            String username = jwtService.extractUsername(jwt);
            UserEntity user = userRepository.findByEmail(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (MalformJwtException e) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
