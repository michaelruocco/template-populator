package uk.co.mruoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;


public class FileOutputStreamConverterTest {

    private final FileCreator fileCreator = new FileCreator();
    private final FileDeleter fileDeleter = new FileDeleter();
    private final FileOutputStreamConverter converter = new FileOutputStreamConverter();

    @Test
    public void shouldCreateFileIfDoesNotExist() throws IOException {
        String filePath = "test/create.txt";

        OutputStream stream = converter.toOutputStream(filePath);

        try {
            assertThat(Files.exists(Paths.get(filePath))).isTrue();
        } finally {
            stream.close();
            fileDeleter.deleteFileIfExists(filePath);
        }
    }

    @Test
    public void shouldUseFileIfAlreadyExists() throws IOException {
        String filePath = "test/create.txt";
        fileCreator.createFile(filePath);

        OutputStream stream = converter.toOutputStream(filePath);

        try {
            assertThat(Files.exists(Paths.get(filePath))).isTrue();
        } finally {
            stream.close();
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
    public void shouldCreateParentDirectoryIfDoesNotExist() throws IOException {
        String filePath = "test/test-parent/create.txt";

        OutputStream stream = converter.toOutputStream(filePath);

        try {
            assertThat(Files.exists(Paths.get(filePath))).isTrue();
        } finally {
            stream.close();
            fileDeleter.deleteParentIfExists(filePath);
        }
    }

}
