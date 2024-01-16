package ru.alex.manga_manager.service.update;

public interface UpdateComponent<T, E> {

    void execute(T component, E updateComponent);
}
