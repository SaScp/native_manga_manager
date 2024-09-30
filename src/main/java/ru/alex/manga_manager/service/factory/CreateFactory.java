package ru.alex.manga_manager.service.factory;

public interface CreateFactory<T, D> {

    T create(D entity);
}
