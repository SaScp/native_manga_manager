package ru.alex.manga_manager.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yandexparser.baseLogic.Cloud;

import java.util.List;

@Configuration
@EnableWebMvc
public class MySpringConfiguration implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new FilterParamResolver(false));
        resolvers.add(new SearchParamResolver(false));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Value("${yandex.disk.token}")
    private String yandexToken;

    @Bean
    public Cloud cloud() {
        return Cloud.builder().token(yandexToken).build();
    }
}
