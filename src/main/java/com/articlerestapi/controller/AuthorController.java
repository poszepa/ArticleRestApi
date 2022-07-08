package com.articlerestapi.controller;

import com.articlerestapi.entity.Author;
import com.articlerestapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @PostMapping
    ResponseEntity<Author> saveAuthor(@RequestBody @Valid Author author, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Author authorSavedInDB = authorRepository.save(author);
        URI savedAuthor = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(authorSavedInDB.getId())
                .toUri();
        return ResponseEntity.created(savedAuthor).body(authorSavedInDB);
    }

}
