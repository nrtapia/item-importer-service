package com.ntapia.itemimporter.application.item.importer.parser;

import com.ntapia.itemimporter.application.item.importer.ItemDTO;
import com.ntapia.itemimporter.application.item.importer.ItemImporterCommand;

public class CsvFileParserStrategy extends FileParserReader {

    @Override
    protected ItemDTO parseLineToItemDto(ItemImporterCommand itemImporterCommand, String line) {
        try {
            var lineValues = line.split(itemImporterCommand.separator());
            return new ItemDTO(lineValues[0], lineValues[1]);
        } catch (Exception e) {
            throw new CsvParserException(String.format("Error parsing line: %s", line), e);
        }
    }
}
