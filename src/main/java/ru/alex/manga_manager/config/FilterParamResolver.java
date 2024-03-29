package ru.alex.manga_manager.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.util.annotation.FilterParam;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;


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
        List<String> types = request.getParameterValues("type") != null ?
                Arrays.stream(Objects.requireNonNull(request.getParameterValues("type"))).toList() : null;
        List<String> genres = request.getParameterValues("genre") != null ?
                Arrays.stream(Objects.requireNonNull(request.getParameterValues("genre"))).toList() : null;

        return new FilterEntity(types,
                request.getParameter("pageNumber"),
                genres,
                request.getParameter("order"),
                request.getParameter("pageSize"));
    }
}
