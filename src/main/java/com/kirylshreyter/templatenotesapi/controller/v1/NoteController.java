package com.kirylshreyter.templatenotesapi.controller.v1;

import com.kirylshreyter.templatenotesapi.assembler.NoteModelAssembler;
import com.kirylshreyter.templatenotesapi.exception.NoteNotFoundException;
import com.kirylshreyter.templatenotesapi.exception.UserNotFoundException;
import com.kirylshreyter.templatenotesapi.model.Note;
import com.kirylshreyter.templatenotesapi.repository.NoteRepository;
import com.kirylshreyter.templatenotesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class NoteController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteModelAssembler assembler;

    @GetMapping("/users/{userId}/notes")
    public CollectionModel<EntityModel<Note>> all(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        List<EntityModel<Note>> notes = noteRepository.findByUserId(userId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(notes, linkTo(methodOn(NoteController.class).all(userId)).withSelfRel());
    }

    @PostMapping("/users/{userId}/notes")
    ResponseEntity<?> newNote(@PathVariable Long userId, @RequestBody Note newNote) {
        EntityModel<Note> entityModel = userRepository.findById(userId).map(user -> {
            newNote.setUser(user);
            return assembler.toModel(noteRepository.save(newNote));
        }).orElseThrow(() -> new UserNotFoundException(userId));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/users/{userId}/notes/{id}")
    public EntityModel<Note> one(@PathVariable Long userId, @PathVariable Long id) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        Note note = noteRepository.findByUserIdAndId(userId, id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        return assembler.toModel(note);
    }

    @PutMapping("/users/{userId}/notes/{id}")
    ResponseEntity<?> replaceUser(@RequestBody Note newNote, @PathVariable Long userId, @PathVariable Long id) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        Note updatedNote = noteRepository.findById(id)
                .map(note -> {
                    note.setBody(newNote.getBody());
                    return noteRepository.save(note);
                })
                .orElseGet(() -> {
                    newNote.setId(id);
                    return noteRepository.save(newNote);
                });
        EntityModel<Note> entityModel = assembler.toModel(updatedNote);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/users/{userId}/notes/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long userId, @PathVariable Long id) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        noteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
