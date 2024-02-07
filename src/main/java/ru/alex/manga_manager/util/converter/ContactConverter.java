package ru.alex.manga_manager.util.converter;

import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;

public class ContactConverter implements ConverterStrategy<Contact, ContactDto>{
    @Override
    public Contact convertTo(ContactDto convertEntity) {
        return null;
    }

    @Override
    public ContactDto convertFrom(Contact convertEntity) {
        return null;
    }
}
