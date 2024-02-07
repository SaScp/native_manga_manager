package ru.alex.manga_manager.util.converter;

public interface Converter<T, E> {
    T convertTo(E convertEntity);
    E convertFrom(T convertEntity);
}
