package com.kirylshreyter.templatenotesapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator") Long id;
    private String email;

    User() {
    }

    User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
