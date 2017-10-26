package uk.co.mruoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCreator {

    public File createFile(String path) {
        return createFile(Paths.get(path));
    }

    public File createFile(Path path) {
        try {
            createParentIfDoesNotExist(path);
            Path createdPath = Files.createFile(path);
            return createdPath.toFile();
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

    public File createDirectories(String path) {
        return createDirectories(Paths.get(path));
    }

    public File createDirectories(Path path) {
        try {
            Path createdPath = Files.createDirectories(path);
            return createdPath.toFile();
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

    private void createParentIfDoesNotExist(Path path) {
        Path parent = path.getParent();
        if (Files.exists(parent))
            return;
        createDirectories(path.getParent());
    }

}
