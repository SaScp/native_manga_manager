package ru.alex.manga_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.contact.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
