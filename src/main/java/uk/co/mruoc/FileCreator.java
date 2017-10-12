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
            Path createdPath = Files.createFile(path);
            return createdPath.toFile();
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

    public File createDirectory(String path) {
        return createDirectory(Paths.get(path));
    }

    public File createDirectory(Path path) {
        try {
            Path createdPath = Files.createDirectory(path);
            return createdPath.toFile();
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

}
