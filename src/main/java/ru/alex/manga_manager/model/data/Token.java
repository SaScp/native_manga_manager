package ru.alex.manga_manager.model.data;

import java.time.Instant;
import java.util.List;

public record Token(String id, String subject, List<String> authorities, Instant createAt, Instant expireAt) {
}
