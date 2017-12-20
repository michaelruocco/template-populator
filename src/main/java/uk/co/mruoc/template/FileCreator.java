package uk.co.mruoc.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileCreator.class);

    public File createFile(String path) {
        return createFile(Paths.get(path));
    }

    public File createFile(Path path) {
        try {
            createParentIfDoesNotExist(path);
            LOGGER.info("creating file " + path.toAbsolutePath());
            Path createdPath = Files.createFile(path);
            LOGGER.info("created file " + createdPath.toAbsolutePath());
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
            LOGGER.info("creating directories " + path.toAbsolutePath());
            Path createdPath = Files.createDirectories(path);
            LOGGER.info("created directories " + createdPath.toAbsolutePath());
            return createdPath.toFile();
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

    private void createParentIfDoesNotExist(Path path) {
        Path parent = path.getParent();
        boolean exists = Files.exists(parent);
        LOGGER.info("parent file " + parent.toAbsolutePath() + " exists " + exists);
        if (exists)
            return;
        createDirectories(path.getParent());
    }

}
