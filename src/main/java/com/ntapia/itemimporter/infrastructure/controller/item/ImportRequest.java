package com.ntapia.itemimporter.infrastructure.controller.item;

import com.ntapia.itemimporter.application.item.importer.FileFormat;

public record ImportRequest(FileFormat fileFormat, String fileName, String encoding, String separator) {
}
