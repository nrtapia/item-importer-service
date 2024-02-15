package com.ntapia.itemimporter.application.item.importer.parser;

import com.ntapia.itemimporter.application.item.importer.ItemDTO;
import com.ntapia.itemimporter.application.item.importer.ItemImporterCommand;

import java.util.function.Consumer;

public interface FileParserStrategy {

    void process(ItemImporterCommand itemImporterCommand, Consumer<ItemDTO> itemDTOConsumer);
}
