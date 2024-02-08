package ru.alex.manga_manager.security.configurer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.repository.LogoutRepository;
import ru.alex.manga_manager.security.authntication.JwtAuthenticationConverter;
import ru.alex.manga_manager.security.authntication.TokenAuthUserDetailsService;
import ru.alex.manga_manager.security.filter.DeniedRequestFilter;
import ru.alex.manga_manager.security.filter.JwtLogoutFilter;
import ru.alex.manga_manager.security.filter.RefreshTokenFilter;
import ru.alex.manga_manager.security.jwt.deserializer.AccessTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.deserializer.RefreshTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.serializer.AccessTokenJwsStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.RefreshTokenJweStringSerializer;


@Component
@RequiredArgsConstructor
public class RequestConfigurer extends AbstractHttpConfigurer<RequestConfigurer, HttpSecurity> {


    private AccessTokenJwsStringSerializer accessTokenJwsStringSerializer;

    private RefreshTokenJweStringSerializer refreshTokenJweStringSerializer;

    private RefreshTokenJwsStringDeserializer refreshTokenJwsStringDeserializer;

    private AccessTokenJwsStringDeserializer accessTokenJwsStringDeserializer;

    private LogoutRepository logoutRepository;
    @Override
    public void init(HttpSecurity builder) throws Exception {
        super.init(builder);
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {

        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager,
                new JwtAuthenticationConverter(refreshTokenJwsStringDeserializer, accessTokenJwsStringDeserializer));
        authenticationFilter.setSuccessHandler((request, response, authentication) -> CsrfFilter.skipRequest(request));
        authenticationFilter.setFailureHandler((request, response, exception) -> response.sendError(HttpStatus.FORBIDDEN.value()));
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(new TokenAuthUserDetailsService(this.logoutRepository));

        RefreshTokenFilter refreshTokenFilter = new RefreshTokenFilter();
        refreshTokenFilter.setAccessTokenJwsStringSerializer(accessTokenJwsStringSerializer);
        refreshTokenFilter.setRefreshTokenJweStringSerializer(refreshTokenJweStringSerializer);

        JwtLogoutFilter jwtLogoutFilter =  new JwtLogoutFilter(this.logoutRepository);

        DeniedRequestFilter deniedRequestFilter = new DeniedRequestFilter();


        builder.addFilterBefore(authenticationFilter, CsrfFilter.class)
                .addFilterBefore(refreshTokenFilter, ExceptionTranslationFilter.class)
                .addFilterAfter(jwtLogoutFilter, ExceptionTranslationFilter.class)
                .addFilterBefore(deniedRequestFilter, DisableEncodeUrlFilter.class)
                .authenticationProvider(authenticationProvider);
    }

    public RequestConfigurer accessTokenJwsStringSerializer(AccessTokenJwsStringSerializer accessTokenJwsStringSerializer) {
        this.accessTokenJwsStringSerializer = accessTokenJwsStringSerializer;
        return this;
    }

    public RequestConfigurer refreshTokenJweStringSerializer(RefreshTokenJweStringSerializer refreshTokenJweStringSerializer) {
        this.refreshTokenJweStringSerializer = refreshTokenJweStringSerializer;
        return this;
    }

    public RequestConfigurer refreshTokenJwsStringDeserializer(RefreshTokenJwsStringDeserializer refreshTokenJwsStringDeserializer) {
        this.refreshTokenJwsStringDeserializer = refreshTokenJwsStringDeserializer;
        return this;
    }

    public RequestConfigurer accessTokenJwsStringDeserializer(AccessTokenJwsStringDeserializer accessTokenJwsStringDeserializer) {
        this.accessTokenJwsStringDeserializer = accessTokenJwsStringDeserializer;
        return this;
    }

    public RequestConfigurer logoutRepository(LogoutRepository logoutRepository) {
        this.logoutRepository = logoutRepository;
        return this;
    }
}
