package uk.co.mruoc.template;

import org.junit.Test;
import uk.co.mruoc.template.BasicTemplatePopulationParams.BasicTemplatePopulationParamsBuilder;
import uk.co.mruoc.template.FileTemplatePopulationParams.FileTemplatePopulationParamsBuilder;
import uk.co.mruoc.properties.FileContentLoader;
import uk.co.mruoc.properties.FileSystemFileContentLoader;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultTemplatePopulatorTest {

    private final FileDeleter fileDeleter = new FileDeleter();
    private final FileContentLoader contentLoader = new FileSystemFileContentLoader();
    private final TemplatePopulator populator = new DefaultTemplatePopulator();

    @Test
    public void shouldPopulateTemplateUsingBasicParams() throws UnsupportedEncodingException {
        String outputPath = "test/output.txt";
        fileDeleter.deleteFileIfExists(outputPath);
        try {
            TemplatePopulationParams params = new BasicTemplatePopulationParamsBuilder()
                    .setTemplateContent("my {{var1}} {{var2}}")
                    .setProperty("var1", "test")
                    .setProperty("var2", "template")
                    .setOutputPath(outputPath)
                    .build();

            populator.populate(params);

            String result = contentLoader.loadContent(outputPath);
            assertThat(result).isEqualTo("my test template");
        } finally {
            fileDeleter.deleteFileIfExists(outputPath);
        }
    }

    @Test
    public void shouldPopulateTemplateUsingFileParams() throws UnsupportedEncodingException {
        FileTemplatePopulationPaths paths = new FakeFileTemplatePopulationPaths();
        fileDeleter.deleteFileIfExists(paths.getOutputPath());
        try {
            TemplatePopulationParams params = new FileTemplatePopulationParamsBuilder()
                    .setPaths(paths)
                    .build();

            populator.populate(params);

            String result = contentLoader.loadContent(paths.getOutputPath());
            assertThat(result).isEqualTo("my file testing templates");
        } finally {
            fileDeleter.deleteFileIfExists(paths.getOutputPath());
        }
    }

    @Test
    public void shouldCreateParentFolderWhenPopulatingTemplate() throws UnsupportedEncodingException {
        String outputPath = "test/folder/output.txt";
        fileDeleter.deleteParentIfExists(outputPath);
        try {
            TemplatePopulationParams params = new BasicTemplatePopulationParamsBuilder()
                    .setTemplateContent("my {{var1}} {{var2}}")
                    .setProperty("var1", "test")
                    .setProperty("var2", "template")
                    .setOutputPath(outputPath)
                    .build();

            populator.populate(params);

            String result = contentLoader.loadContent(outputPath);
            assertThat(result).isEqualTo("my test template");
        } finally {
            fileDeleter.deleteParentIfExists(outputPath);
        }
    }

}
