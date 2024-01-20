package ru.alex.manga_manager.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.alex.manga_manager.security.configurer.RequestConfigurer;
import ru.alex.manga_manager.security.jwt.deserializer.DefaultAccessTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.deserializer.DefaultRefreshTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.serializer.DefaultAccessTokenJwsStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.DefaultRefreshTokenJweStringSerializer;

import java.text.ParseException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public RequestConfigurer requestConfigurer(@Value("${jwt.secret.access}") String accessToken,
                                               @Value("${jwt.secret.refresh}") String refreshToken,
                                               JdbcTemplate jdbcTemplate) throws JOSEException, ParseException {
        return new RequestConfigurer().accessTokenJwsStringSerializer(
                new DefaultAccessTokenJwsStringSerializer(
                        new MACSigner(OctetSequenceKey.parse(accessToken)))
                )
                .refreshTokenJweStringSerializer(
                        new DefaultRefreshTokenJweStringSerializer(
                                new DirectEncrypter(OctetSequenceKey.parse(refreshToken)))
                )
                .accessTokenJwsStringDeserializer(
                        new DefaultAccessTokenJwsStringDeserializer(
                                new MACVerifier(OctetSequenceKey.parse(accessToken)))
                )
                .refreshTokenJwsStringDeserializer(
                        new DefaultRefreshTokenJwsStringDeserializer(
                                new DirectDecrypter(OctetSequenceKey.parse(refreshToken)))
                )
                .jdbcTemplate(jdbcTemplate);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RequestConfigurer requestConfigurer) throws Exception {

        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/v1/admin/**").
                        hasRole("ADMIN")
                        .requestMatchers("/v1/user/**", "v1/{id}/comment/add", "v1/{id}/comment/update/", "v1/{id}/comment/delete/")
                        .hasRole("USER")
                        .anyRequest()
                        .permitAll()
        );


        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.apply(requestConfigurer);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



}
