package uk.co.mruoc;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public class FileOutputStreamConverterTest {

    private final FileCreator fileCreator = new FileCreator();
    private final FileDeleter fileDeleter = new FileDeleter();
    private final FileOutputStreamConverter converter = new FileOutputStreamConverter();

    @Test
    public void shouldCreateFileIfDoesNotExist() {
        String filePath = "test/create.txt";

        converter.toOutputStream(filePath);

        try {
            assertThat(Files.exists(Paths.get(filePath))).isTrue();
        } finally {
            fileDeleter.deleteFileIfExists(filePath);
        }
    }

    @Test
    public void shouldUseFileIfAlreadyExists() throws IOException {
        String filePath = "test/create.txt";
        fileCreator.createFile(filePath);

        converter.toOutputStream(filePath);

        try {
            assertThat(Files.exists(Paths.get(filePath))).isTrue();
        } finally {
            fileDeleter.deleteFileIfExists(filePath);
        }
    }

    @Test(expected = TemplatePopulationException.class)
    public void shouldErrorIfFileExistsAsDirectory() {
        String filePath = "test/create.txt";
        fileCreator.createDirectories(filePath);

        try {
            converter.toOutputStream(filePath);
        } finally {
            fileDeleter.deleteFileIfExists(filePath);
        }
    }

    @Test
    public void shouldCreateParentDirectoryIfDoesNotExist() {
        String filePath = "test/test-parent/create.txt";

        converter.toOutputStream(filePath);

        try {
            assertThat(Files.exists(Paths.get(filePath))).isTrue();
        } finally {
            fileDeleter.deleteParentIfExists(filePath);
        }
    }

}
