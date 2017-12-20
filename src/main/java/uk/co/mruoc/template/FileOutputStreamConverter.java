package uk.co.mruoc.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOutputStreamConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileOutputStreamConverter.class);

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
        boolean exists = Files.exists(path);
        boolean regularFile = Files.isRegularFile(path);
        LOGGER.info("file " + path.toAbsolutePath() + " exists " + exists + " regular file " + regularFile);
        if (exists && regularFile)
            return path.toFile();
        return fileCreator.createFile(path);
    }

}
