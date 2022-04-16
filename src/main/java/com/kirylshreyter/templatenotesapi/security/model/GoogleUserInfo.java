package com.kirylshreyter.templatenotesapi.security.model;

import java.util.Map;

public class GoogleUserInfo {
    private final Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return (String) attributes.get("sub");
    }

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getFirstName() {
        return (String) attributes.get("given_name");
    }

    public String getLastName() {
        return (String) attributes.get("family_name");
    }

    public String getLocale() {
        return (String) attributes.get("locale");
    }

    public String getPictureUrl() {
        return (String) attributes.get("picture");
    }

    @Override
    public String toString() {
        return "GoogleUserInfo{" +
                "attributes=" + attributes +
                '}';
    }
}
