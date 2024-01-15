package ru.alex.manga_manager.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.alex.manga_manager.model.data.Token;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.security.authntication.TokenUser;
import ru.alex.manga_manager.security.jwt.factory.AccessFactory;
import ru.alex.manga_manager.security.jwt.factory.DefaultAccessFactory;
import ru.alex.manga_manager.security.jwt.factory.DefaultRefreshFactory;
import ru.alex.manga_manager.security.jwt.factory.RefreshFactory;
import ru.alex.manga_manager.security.jwt.serializer.AccessTokenJwsStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.RefreshTokenJweStringSerializer;


import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

public class RefreshTokenFilter extends OncePerRequestFilter {

    private RequestMatcher requestMatcher = new AntPathRequestMatcher("/jwt/refresh", HttpMethod.POST.name());

    private SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    private AccessFactory accessFactory = new DefaultAccessFactory();

    private RefreshFactory refreshFactory = new DefaultRefreshFactory();
    private AccessTokenJwsStringSerializer accessTokenJwsStringSerializer = Object::toString;

    private ObjectMapper objectMapper = new ObjectMapper();

    private RefreshTokenJweStringSerializer refreshTokenJweStringSerializer = Objects::toString;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException,
            IOException {
        if (this.requestMatcher.matches(request)) {
            if (this.securityContextRepository.containsContext(request)) {
                var context = this.securityContextRepository.loadDeferredContext(request).get();
                if (context != null && context.getAuthentication() instanceof PreAuthenticatedAuthenticationToken &&
                        context.getAuthentication().getPrincipal() instanceof TokenUser user &&
                        context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("JWT_REFRESH"))) {
                    Token accessToken = this.accessFactory.apply(user.getToken());
                    Token refreshToken = this.refreshFactory.apply(context.getAuthentication());

                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    this.objectMapper.writeValue(response.getWriter(),
                            new Tokens(this.accessTokenJwsStringSerializer.apply(accessToken),
                                    accessToken.expireAt().toString(),
                                    this.refreshTokenJweStringSerializer.apply(refreshToken),
                                    refreshToken.expireAt().toString()));
                    return;
                }
            }

            throw new AccessDeniedException("User must be auth with JWT");
        }
        filterChain.doFilter(request, response);
    }

    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    public void setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    public void setAccessFactory(AccessFactory accessFactory) {
        this.accessFactory = accessFactory;
    }

    public void setAccessTokenJwsStringSerializer(AccessTokenJwsStringSerializer accessTokenJwsStringSerializer) {
        this.accessTokenJwsStringSerializer = accessTokenJwsStringSerializer;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setRefreshFactory(RefreshFactory refreshFactory) {
        this.refreshFactory = refreshFactory;
    }

    public void setRefreshTokenJweStringSerializer(RefreshTokenJweStringSerializer refreshTokenJweStringSerializer) {
        this.refreshTokenJweStringSerializer = refreshTokenJweStringSerializer;
    }
}