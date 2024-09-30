package ru.alex.manga_manager.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.comment.CommentDto;
import ru.alex.manga_manager.model.dto.contact.ContactDto;

import java.util.List;

@Mapper
public interface ContactMapper {


    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDto contactToContactDto(Contact contact);

    Contact contactDtoToContact(ContactDto commentDto);

    List<ContactDto> contactsToContactDtos(List<Contact> comments);

    List<Contact> contactDtosToContacts(List<ContactDto> commentDtos);
}
