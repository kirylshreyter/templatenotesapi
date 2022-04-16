package com.kirylshreyter.templatenotesapi.controller.v1;

import com.kirylshreyter.templatenotesapi.assembler.NoteModelAssembler;
import com.kirylshreyter.templatenotesapi.model.Note;
import com.kirylshreyter.templatenotesapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class NoteController {
    @Autowired
    private NoteModelAssembler assembler;

    @Autowired
    private NoteService noteService;

    @GetMapping("/users/{userId}/notes")
    public CollectionModel<EntityModel<Note>> all(@PathVariable @NotBlank Long userId) {
        List<EntityModel<Note>> notes = noteService.getNotesByUserId(userId)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(notes, linkTo(methodOn(NoteController.class).all(userId)).withSelfRel());
    }

    @PostMapping("/users/{userId}/notes")
    ResponseEntity<?> newNote(@PathVariable @NotBlank Long userId, @RequestBody Note newNote) {
        EntityModel<Note> entityModel = assembler.toModel(noteService.saveNoteByUserId(userId, newNote));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/users/{userId}/notes/{id}")
    public EntityModel<Note> one(@PathVariable @NotBlank Long userId, @PathVariable @NotBlank Long id) {
        return assembler.toModel(noteService.getNoteByUserIdAndId(userId, id));
    }

    @PutMapping("/users/{userId}/notes/{id}")
    ResponseEntity<?> replaceUser(@RequestBody Note newNote, @PathVariable @NotBlank Long userId, @PathVariable @NotBlank Long id) {
        EntityModel<Note> entityModel = assembler.toModel(noteService.updateNoteByUserIdAndId(userId, id, newNote));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/users/{userId}/notes/{id}")
    ResponseEntity<?> deleteUser(@PathVariable @NotBlank Long userId, @PathVariable @NotBlank Long id) {
        noteService.deleteNoteByUserIdAndId(userId, id);
        return ResponseEntity.noContent().build();
    }
}
