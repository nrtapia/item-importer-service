package com.ntapia.itemimporter.domain;

import java.util.Objects;

public class Currency {

    private String id;
    private String description;

    public Currency(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Currency() {
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
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
                ", description='" + description + '\'' +
                '}';
    }
}
