package com.ntapia.itemimporter.domain;

import java.util.Objects;

public class User {

    private String id;
    private String nickname;

    public User(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User currency = (User) o;
        return Objects.equals(id, currency.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
