import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

    public class StorageReader {
        private static final StorageReader instance = new StorageReader();
        private static final ObjectMapper mapper = new ObjectMapper();

        private StorageReader() {}

        public static StorageReader getInstance() {
            return instance;
        }

        public StorageConfig readStorageConfig() throws IOException {
            File file = new File("src/main/resources/storage.json");
            return mapper.readValue(file, StorageConfig.class);
        }
    }


