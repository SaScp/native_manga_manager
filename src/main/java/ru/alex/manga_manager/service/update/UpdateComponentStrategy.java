package ru.alex.manga_manager.service.update;

public interface UpdateComponentStrategy<T, E> {

    void execute(T component, E updateComponent);
}
