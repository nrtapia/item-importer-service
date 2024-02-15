package com.ntapia.itemimporter.infrastructure.persistence.item;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uniqueId;
    @Column(length = 3)
    private String site;
    @Column(length = 10)
    private String id;
    private BigDecimal price;
    @Column(name = "start_time")
    private String startTime;
    @Column(length = 250)
    private String name;
    @Column(length = 250)
    private String description;
    @Column(length = 250)
    private String nickname;


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Objects.equals(uniqueId, that.uniqueId) && Objects.equals(site, that.site) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, site, id);
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "site='" + site + '\'' +
                ", id='" + id + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", price=" + price +
                ", startTime='" + startTime + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
