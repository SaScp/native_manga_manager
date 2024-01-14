package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.manga_manager.model.data.Contact;
import ru.alex.manga_manager.model.dto.ContactDto;
import ru.alex.manga_manager.repository.RoleRepository;
import ru.alex.manga_manager.service.ContactService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/contact")
public class ContactController {

    @Qualifier("defaultContactService")
    private final ContactService contactService;

    private final RoleRepository repository;

    @PostMapping("/send")
    public ResponseEntity<Contact> sendContactInfo(@RequestBody ContactDto contactDto) throws URISyntaxException {
        return ResponseEntity.created(new URI("/v1/contact/send"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(contactService.save(contactDto));
    }

}
