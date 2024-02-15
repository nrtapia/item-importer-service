package com.ntapia.itemimporter.application.item.importer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.BufferedReader;

public record ItemImporterCommand(FileFormat fileFormat, @JsonIgnore BufferedReader bufferedReader, String encoding, String separator) {
}
