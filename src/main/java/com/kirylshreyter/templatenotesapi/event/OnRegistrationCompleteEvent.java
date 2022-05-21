package com.kirylshreyter.templatenotesapi.event;

import com.kirylshreyter.templatenotesapi.model.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;
import java.util.Objects;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
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
        OnRegistrationCompleteEvent that = (OnRegistrationCompleteEvent) o;
        return getAppUrl().equals(that.getAppUrl()) && getLocale().equals(that.getLocale()) && getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAppUrl(), getLocale(), getUser());
    }

    @Override
    public String toString() {
        return "OnRegistrationCompleteEvent{" +
                "appUrl='" + appUrl + '\'' +
                ", locale=" + locale +
                ", user=" + user +
                ", source=" + source +
                '}';
    }
}
