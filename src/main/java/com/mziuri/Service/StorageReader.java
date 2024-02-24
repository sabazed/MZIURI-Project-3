package com.mziuri.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mziuri.Classes.Product;
import com.mziuri.Classes.StorageConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StorageReader {
    static ObjectMapper mapper=new ObjectMapper();
    private StorageConfig config;
    private static StorageReader reader=null;

    public StorageReader(StorageConfig config) {
        this.config = config;
    }

    public static StorageReader getReader() throws IOException {
        if (reader==null){
            String json= new String(Files.readAllBytes(Paths.get("src/main/resources/storage.json")));
            reader=new StorageReader(mapper.readerFor(StorageConfig.class).readValue(json));
        }
        return reader;
    }

    public StorageConfig getConfig() {
        return config;
    }
    public static void update() throws IOException {
        StorageConfig newConfig=new StorageConfig(reader.getConfig().getPassword());
        mapper.writeValue(new File("src/main/resources/storage.json"),newConfig);
        reader=null;
        reader=StorageReader.getReader();
    }
}
