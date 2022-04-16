package com.kirylshreyter.templatenotesapi.repository;

import com.kirylshreyter.templatenotesapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);

    Optional<Note> findByUserIdAndId(Long userId, Long id);

    boolean existsByUserIdAndId(Long userId, Long id);

    void deleteByUserIdAndId(Long userId, Long id);

    @Transactional
    void deleteByUserId(long userId);
}
