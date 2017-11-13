package uk.co.mruoc.template;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import uk.co.mruoc.template.FileTemplatePopulationParams.FileTemplatePopulationParamsBuilder;

public class FileTemplatePopulationParamsTest {

    private final FileTemplatePopulationParamsBuilder builder = new FileTemplatePopulationParamsBuilder();
    private final FileDeleter fileDeleter = new FileDeleter();

    @Test
    public void shouldReturnOutputPath() throws IOException {
        String outputPath = "test/output.txt";
        builder.setOutputPath(outputPath);

        try {
            FileTemplatePopulationParams params = builder.build();
            assertThat(params.getOutputPath()).isEqualTo(outputPath);
        } finally {
            fileDeleter.deleteFileIfExists(outputPath);
        }
    }

}
