package com.ntapia.itemimporter.infrastructure.config;

import com.ntapia.itemimporter.infrastructure.client.category.CategoryResponse;
import com.ntapia.itemimporter.infrastructure.client.currency.CurrencyResponse;
import com.ntapia.itemimporter.infrastructure.client.item.ItemResponse;
import com.ntapia.itemimporter.infrastructure.client.user.UserResponse;
import feign.Param;
import feign.RequestLine;

public interface MeliRestClient {

    @RequestLine("GET /items/{itemId}")
    ItemResponse getItem(@Param("itemId") String itemId);

    @RequestLine("GET /categories/{categoryId}")
    CategoryResponse getCategory(@Param("categoryId") String categoryId);

    @RequestLine("GET /currencies/{currencyId}")
    CurrencyResponse getCurrency(@Param("currencyId") String currencyId);

    @RequestLine("GET /users/{userId}")
    UserResponse getUser(@Param("userId") String userId);
}
