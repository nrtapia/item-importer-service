package com.ntapia.itemimporter.application.item.importer.parser;

import com.ntapia.itemimporter.application.item.importer.ItemDTO;
import com.ntapia.itemimporter.application.item.importer.ItemImporterCommand;
import com.ntapia.itemimporter.application.item.importer.ItemImporterReadFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public abstract class FileParserReader implements FileParserStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileParserReader.class);

    /**
     * Parses the specified file line into a instance of #ItemDTO
     *
     * @param itemImporterCommand command to execute when the line is parsed
     * @param line                file line
     * @return ItemDTO instance
     */
    protected abstract ItemDTO parseLineToItemDto(ItemImporterCommand itemImporterCommand, String line);

    @Override
    public void process(ItemImporterCommand itemImporterCommand, Consumer<ItemDTO> itemDTOConsumer) {
        var counter = new AtomicInteger(1);
        String line;
        try {
            while ((line = itemImporterCommand.bufferedReader().readLine()) != null) {

                int currentCounter = counter.getAndIncrement();
                LOGGER.debug("Reading file line: {}", currentCounter);
                if (currentCounter > 1) {
                    var itemDTO = this.parseLineToItemDto(itemImporterCommand, line);
                    itemDTOConsumer.accept(itemDTO);
                }
            }
        } catch (IOException e) {
            throw new ItemImporterReadFileException(e);
        } finally {
            try {
                itemImporterCommand.bufferedReader().close();
            } catch (IOException e) {
                LOGGER.error("Failed to close file", e);
            }
        }
    }


}
