package ru.alex.manga_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.Role;


/**Repository for {@link ru.alex.manga_manager.model.data.Role}*/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
