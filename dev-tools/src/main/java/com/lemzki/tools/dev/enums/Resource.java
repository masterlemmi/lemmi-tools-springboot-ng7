package com.lemzki.tools.dev.enums;

public enum Resource {
    SHORTCUTS("ide_shortcuts.csv"), PLUGINS("plugins.csv"), SOMETEXT("text.txt");
    String fileName;


    Resource(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
