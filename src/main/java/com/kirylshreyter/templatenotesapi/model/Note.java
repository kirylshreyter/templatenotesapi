package com.kirylshreyter.templatenotesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notes")
public class Note {
    private @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_generator") Long id;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    Note() {
    }

    Note(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return getId().equals(note.getId()) && Objects.equals(getBody(), note.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBody());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", value='" + body + '\'' +
                '}';
    }
}
