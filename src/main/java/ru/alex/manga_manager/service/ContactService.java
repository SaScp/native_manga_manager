package ru.alex.manga_manager.service;


import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;

import java.util.List;

public interface ContactService {
    Contact save(ContactDto contactDto);
    List<Contact> findAll();
}
