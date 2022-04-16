package com.kirylshreyter.templatenotesapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator") Long id;
    private String email;
    private String pictureUrl;
    private String locale;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String email) {
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getEmail().equals(user.getEmail()) && Objects.equals(getPictureUrl(), user.getPictureUrl()) && Objects.equals(getLocale(), user.getLocale()) && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPictureUrl(), getLocale(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", locale='" + locale + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
