package ru.alex.manga_manager.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import ru.alex.manga_manager.model.data.FilterEntity;
import ru.alex.manga_manager.util.FilterParam;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class FilterParamResolver extends RequestParamMethodArgumentResolver {
    public FilterParamResolver(boolean useDefaultResolution) {
        super(useDefaultResolution);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(FilterParam.class);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        List<String> types = request.getParameterValues("types") != null?
                Arrays.stream(Objects.requireNonNull(request.getParameterValues("types"))).toList() : null;
        List<String> genres = request.getParameterValues("genres") != null?
                Arrays.stream(Objects.requireNonNull(request.getParameterValues("genres"))).toList() : null;

        return new FilterEntity(types,
                request.getParameter("pageNumber"),
                genres,
                request.getParameter("order"),
                request.getParameter("pageSize"));
    }
}
