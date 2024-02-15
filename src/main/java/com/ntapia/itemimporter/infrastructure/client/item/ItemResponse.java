package com.ntapia.itemimporter.infrastructure.client.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

public class ItemResponse {

    @JsonSerialize(as = BigDecimal.class)
    private BigDecimal price;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("category_id")
    private String categoryId;
    @JsonProperty("currency_id")
    private String currencyId;
    @JsonProperty("seller_id")
    private String sellerId;

    @Override
    public String toString() {
        return "ItemResponse{" +
                "price=" + price +
                ", startTime='" + startTime + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", currencyId='" + currencyId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getSellerId() {
        return sellerId;
    }
}
