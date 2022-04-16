package com.kirylshreyter.templatenotesapi.service.impl;

import com.kirylshreyter.templatenotesapi.exception.NoteNotFoundException;
import com.kirylshreyter.templatenotesapi.exception.UserNotFoundException;
import com.kirylshreyter.templatenotesapi.model.Note;
import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.repository.NoteRepository;
import com.kirylshreyter.templatenotesapi.repository.UserRepository;
import com.kirylshreyter.templatenotesapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getNotesByUserId(Long userId) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);

        return noteRepository.findByUserId(userId);
    }

    @Override
    public void deleteNoteByUserIdAndId(Long userId, Long id) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        if (!noteRepository.existsByUserIdAndId(userId, id)) throw new NoteNotFoundException(id);
        noteRepository.deleteByUserIdAndId(userId, id);
    }

    @Override
    public Note getNoteByUserIdAndId(Long userId, Long id) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        return noteRepository.findByUserIdAndId(userId, id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public Note saveNoteByUserId(Long userId, Note newNote) {
        return userRepository.findById(userId).map(user -> {
            newNote.setUser(user);
            return noteRepository.save(newNote);
        }).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public Note updateNoteByUserIdAndId(Long userId, Long id, Note newNote) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        return noteRepository.findByUserIdAndId(userId, id).map(note -> {
            note.setBody(newNote.getBody());
            return noteRepository.save(note);
        }).orElseGet(() -> {
            newNote.setUser(user);
            return noteRepository.save(newNote);
        });
    }
}
