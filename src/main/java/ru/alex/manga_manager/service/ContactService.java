package ru.alex.manga_manager.service;


import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;

public interface ContactService {
    Contact save(ContactDto contactDto);
}
