package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.contact.Contact;
import ru.alex.manga_manager.model.dto.contact.ContactDto;
import ru.alex.manga_manager.service.ContactService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/contact")
public class ContactController {

    @Qualifier("defaultContactService")
    private final ContactService contactService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Contact sendContactInfo(@RequestBody ContactDto contactDto) throws URISyntaxException {
        return contactService.save(contactDto);
    }

}
