package ru.alex.manga_manager.util.exception;

public class MangaNotFoundException extends RuntimeException {
    public MangaNotFoundException(String message) {
        super(message);
    }
}
