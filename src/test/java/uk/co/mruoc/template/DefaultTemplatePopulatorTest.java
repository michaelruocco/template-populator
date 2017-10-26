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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TemplatePopulationParams params = new BasicTemplatePopulationParamsBuilder()
                .setTemplateContent("my {{var1}} {{var2}}")
                .setProperty("var1", "test")
                .setProperty("var2", "template")
                .setOutputStream(outputStream)
                .build();

        populator.populate(params);

        String result = outputStream.toString("UTF8");
        assertThat(result).isEqualTo("my test template");
    }

    @Test
    public void shouldPopulateTemplateUsingFileParams() throws UnsupportedEncodingException {
        String outputPath = "test/output.txt";
        fileDeleter.deleteFileIfExists(outputPath);
        try {
            TemplatePopulationParams params = new FileTemplatePopulationParamsBuilder()
                    .setTemplatePath("test/template.txt")
                    .setPropertiesPath("test/properties.properties")
                    .setOutputPath(outputPath)
                    .build();

            populator.populate(params);

            String result = contentLoader.loadContent(outputPath);
            assertThat(result).isEqualTo("my file testing templates");
        } finally {
            fileDeleter.deleteFileIfExists(outputPath);
        }
    }

}
