package com.ntapia.itemimporter.application.item.importer.parser;

import com.ntapia.itemimporter.application.item.importer.FileFormat;
import com.ntapia.itemimporter.application.item.importer.ItemDTO;
import com.ntapia.itemimporter.application.item.importer.ItemImporterCommand;

import java.util.Map;
import java.util.function.Consumer;

public class FileParserStrategyRouter {

    private FileParserStrategyRouter() {
    }

    private static final Map<FileFormat, FileParserStrategy> STRATEGY_MAP;

    static {
        STRATEGY_MAP = Map.of(
                FileFormat.CSV, new CsvFileParserStrategy(),
                FileFormat.JSON_LINES, new CsvFileParserStrategy()
        );
    }

    public static void apply(ItemImporterCommand itemImporterCommand, Consumer<ItemDTO> itemDTOConsumer) {
        STRATEGY_MAP.get(itemImporterCommand.fileFormat()).process(itemImporterCommand, itemDTOConsumer);
    }
}
