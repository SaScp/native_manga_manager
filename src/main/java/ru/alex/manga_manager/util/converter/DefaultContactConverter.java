package ru.alex.manga_manager.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;

@Component
@RequiredArgsConstructor
public class DefaultContactConverter implements ContactConverter<Contact, ContactDto> {

    private final ModelMapper modelMapper;

    @Override
    public Contact convert(ContactDto convertDto) {
        return this.modelMapper.map(convertDto, Contact.class);
    }
}
