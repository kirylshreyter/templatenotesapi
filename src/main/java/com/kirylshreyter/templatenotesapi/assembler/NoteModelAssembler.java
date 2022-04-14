package com.kirylshreyter.templatenotesapi.assembler;

import com.kirylshreyter.templatenotesapi.controller.v1.NoteController;
import com.kirylshreyter.templatenotesapi.model.Note;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NoteModelAssembler implements RepresentationModelAssembler<Note, EntityModel<Note>> {
    @Override
    public EntityModel<Note> toModel(Note note) {
        return EntityModel.of(note,
                linkTo(methodOn(NoteController.class).one(note.getUser().getId(), note.getId())).withSelfRel(),
                linkTo(methodOn(NoteController.class).all(note.getUser().getId())).withRel("notes"));
    }
}
