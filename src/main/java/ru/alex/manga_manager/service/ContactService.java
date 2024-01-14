package ru.alex.manga_manager.service;


import ru.alex.manga_manager.model.data.Contact;
import ru.alex.manga_manager.model.dto.ContactDto;

public interface ContactService {
    Contact save(ContactDto contactDto);
}
