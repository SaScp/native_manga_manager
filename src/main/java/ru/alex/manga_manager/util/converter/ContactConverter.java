package ru.alex.manga_manager.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;

@Component
public class ContactConverter implements Converter<Contact, ContactDto> {
    private final ModelMapper modelMapper;

    public ContactConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public Contact convertTo(ContactDto convertEntity) {
        return modelMapper.map(convertEntity, Contact.class);
    }

    @Override
    public ContactDto convertFrom(Contact convertEntity) {
        return modelMapper.map(convertEntity, ContactDto.class);
    }
}
