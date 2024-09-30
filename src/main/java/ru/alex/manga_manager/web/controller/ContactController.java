package ru.alex.manga_manager.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;
import ru.alex.manga_manager.service.ContactService;
import ru.alex.manga_manager.util.mapper.ContactMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/contact")
@Tag(name = "ContactController", description = "Контроллер для взаимодействия с Контактами пользователя")
public class ContactController {

    @Qualifier("defaultContactService")
    private final ContactService contactService;

    @Operation(
            summary = "Оставить свои контакты"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendContactInfo(@RequestBody ContactDto contactDto) throws URISyntaxException {
        contactService.save(contactDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public List<ContactDto> findAllContactInfo() {
        return ContactMapper.INSTANCE.contactsToContactDtos(contactService.findAll());
    }
}
