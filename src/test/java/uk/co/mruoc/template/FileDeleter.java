package uk.co.mruoc.template;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDeleter {

    public void deleteFileIfExists(String path) {
        deleteIfExists(Paths.get(path));
    }

    public void deleteParentIfExists(String path) {
        deleteFileIfExists(path);
        deleteIfExists(Paths.get(path).getParent());
    }

    private void deleteIfExists(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
