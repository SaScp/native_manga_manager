package ru.alex.manga_manager.util.converter;

public interface ConverterStrategy<T, E> {
    T convertTo(E convertEntity);
    E convertFrom(T convertEntity);
}
