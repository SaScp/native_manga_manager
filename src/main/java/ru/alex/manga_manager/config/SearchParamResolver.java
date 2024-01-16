package ru.alex.manga_manager.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import ru.alex.manga_manager.model.data.SearchEntity;
import ru.alex.manga_manager.util.annotation.SearchParam;

public class SearchParamResolver extends RequestParamMethodArgumentResolver {
    public SearchParamResolver(boolean useDefaultResolution) {
        super(useDefaultResolution);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SearchParam.class);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        return new SearchEntity(request.getParameter("query"), request.getParameter("page"));
    }


}
