package uk.co.mruoc.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOutputStreamConverter {

    private final FileCreator fileCreator = new FileCreator();

    public OutputStream toOutputStream(String path) {
        try {
            File file = createFileIfDoesNotExist(path);
            return new FileOutputStream(file);
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

    private File createFileIfDoesNotExist(String stringPath) {
        Path path = Paths.get(stringPath);
        if (Files.exists(path) && Files.isRegularFile(path))
            return path.toFile();
        return fileCreator.createFile(path);
    }

}
