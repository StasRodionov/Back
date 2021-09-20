package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.File;

import java.time.LocalDate;
import java.util.List;

public class FileStubs {

    public static List<File> getFiles() {
        return List.of(File.builder()
                .id(1L)
                .name("file.docx")
                .size(11000L)
                .key("d2sfefqf32f4gf23f13t42gq2evg23g2")
                .uploadDate(LocalDate.now())
                .build());
    }
}
