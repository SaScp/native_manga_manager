package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;
import ru.alex.manga_manager.repository.ContactRepository;
import ru.alex.manga_manager.service.ContactService;
import ru.alex.manga_manager.util.converter.contact.ContactConverter;


import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultContactService implements ContactService {

    @Qualifier("defaultContactConverter")
    private final ContactC converter;

    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public Contact save(ContactDto contactDto) {
        Contact contact = this.converter.convert(contactDto);
        contact.setId(UUID.randomUUID().toString());
        contact.setCreateAt(Date.from(Instant.now()));
        return this.contactRepository.save(contact);
    }
}
