package ru.alex.manga_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.User;


import java.util.Optional;

/**Repository for {@link ru.alex.manga_manager.model.data.User}*/
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
