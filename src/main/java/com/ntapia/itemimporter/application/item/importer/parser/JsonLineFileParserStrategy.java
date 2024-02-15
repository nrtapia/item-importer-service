package com.ntapia.itemimporter.application.item.importer.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntapia.itemimporter.application.item.importer.ItemDTO;
import com.ntapia.itemimporter.application.item.importer.ItemImporterCommand;

public class JsonLineFileParserStrategy extends FileParserReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected ItemDTO parseLineToItemDto(ItemImporterCommand itemImporterCommand, String line) {
        try {
            return objectMapper.readValue(line, ItemDTO.class);
        } catch (JsonProcessingException e) {
            throw new JsonLineParserException(String.format("Error parsing line: %s", line), e);
        }
    }
}
