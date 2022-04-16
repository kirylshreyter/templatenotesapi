package com.kirylshreyter.templatenotesapi.service;

import com.kirylshreyter.templatenotesapi.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getNotesByUserId(Long userId);

    void deleteNoteByUserIdAndId(Long userId, Long id);

    Note getNoteByUserIdAndId(Long userId, Long id);

    Note saveNoteByUserId(Long userId, Note newNote);

    Note updateNoteByUserIdAndId(Long userId, Long id, Note newNote);
}
