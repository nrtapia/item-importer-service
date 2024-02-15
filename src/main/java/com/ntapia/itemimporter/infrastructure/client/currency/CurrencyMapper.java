package com.ntapia.itemimporter.infrastructure.client.currency;

import com.ntapia.itemimporter.domain.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {

    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    Currency responseToDomain(CurrencyResponse currencyResponse);
}
