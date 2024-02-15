package com.ntapia.itemimporter.infrastructure.controller.item;

import com.ntapia.itemimporter.application.item.importer.FileFormat;
import com.ntapia.itemimporter.application.item.importer.ItemImporterCommand;
import com.ntapia.itemimporter.application.item.importer.ItemImporterHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/v1/items")
public class ItemsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsResource.class);

    private final ItemImporterHandler itemImporterHandler;

    public ItemsResource(ItemImporterHandler itemImporterHandler) {
        this.itemImporterHandler = itemImporterHandler;
    }

    @PostMapping(value = "/import", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> doImport(@RequestParam @Valid FileFormat fileFormat,
                                           @RequestParam @NotBlank String encoding,
                                           @RequestParam @NotBlank String separator,
                                           @RequestPart MultipartFile file) throws IOException {
        LOGGER.info("Items import fileFormat: [{}] encoding: [{}] separator: [{}]", fileFormat, encoding, separator);

        var inputStream = file.getInputStream();

        try (var bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding))) {
            var itemImporterCommand = new ItemImporterCommand(fileFormat, bufferedReader, encoding, separator);
            itemImporterHandler.execute(itemImporterCommand);
        } catch (Exception ex) {
            LOGGER.error("Failed to process file: {}", ex.getMessage(), ex);
        }
        return new ResponseEntity<>("File will be imported", HttpStatus.ACCEPTED);
    }
}
