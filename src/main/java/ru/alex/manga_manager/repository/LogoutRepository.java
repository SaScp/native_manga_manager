package ru.alex.manga_manager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.entity.Logout;

@Repository
public interface LogoutRepository extends MongoRepository<Logout, String> {
}
