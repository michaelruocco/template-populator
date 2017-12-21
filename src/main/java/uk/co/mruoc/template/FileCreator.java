package uk.co.mruoc.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileCreator.class);

    public File createFile(String path) {
        try {
            File file = new File(path);
            createParentIfDoesNotExist(file);
            LOGGER.info("create file " + file.getAbsolutePath() + " success " + file.createNewFile());
            return file;
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

    public void createParentIfDoesNotExist(File file) {
        File parentDirectory = file.getParentFile();
        createDirectories(parentDirectory);
    }

    public void createDirectories(String path) {
        createDirectories(new File(path));
    }

    public void createDirectories(File file) {
        LOGGER.info("directory " + file.getAbsolutePath() + " exists " + file.exists());
        if (!file.exists())
            LOGGER.info("directory " + file.getAbsolutePath() + " created " + file.mkdirs());
    }

}
