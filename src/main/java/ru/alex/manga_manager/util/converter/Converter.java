package ru.alex.manga_manager.util.converter;

public interface Converter<T, E> {
    T convert(E convertDto);
}
