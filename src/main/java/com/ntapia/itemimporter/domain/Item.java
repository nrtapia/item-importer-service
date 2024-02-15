package com.ntapia.itemimporter.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String id;
    private String siteId;
    private BigDecimal price;
    private String startTime;
    private Category category;
    private Currency currency;
    private User seller;

    public Item(String id, String siteId, BigDecimal price, String startTime, Category category, Currency currency, User seller) {
        this.id = id;
        this.siteId = siteId;
        this.price = price;
        this.startTime = startTime;
        this.category = category;
        this.currency = currency;
        this.seller = seller;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(siteId, item.siteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", site='" + siteId + '\'' +
                ", price=" + price +
                ", startTime='" + startTime + '\'' +
                ", category=" + category +
                ", currency=" + currency +
                ", seller=" + seller +
                '}';
    }
}
