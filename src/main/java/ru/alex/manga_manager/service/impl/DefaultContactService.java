package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alex.manga_manager.model.data.Contact;
import ru.alex.manga_manager.model.dto.ContactDto;
import ru.alex.manga_manager.repository.ContactRepository;
import ru.alex.manga_manager.service.ContactService;
import ru.alex.manga_manager.util.converter.ContactConverter;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultContactService implements ContactService {

    @Qualifier("defaultContactConverter")
    private final ContactConverter<Contact, ContactDto> converter;

    private final ContactRepository contactRepository;

    @Override
    public Contact save(ContactDto contactDto) {
        Contact contact = this.converter.convert(contactDto);
        contact.setId(UUID.randomUUID().toString());

        return this.contactRepository.save(contact);
    }
}
