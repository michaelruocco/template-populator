package uk.co.mruoc;

import org.junit.Test;
import uk.co.mruoc.FileTemplatePopulationParams.FileTemplatePopulationParamsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTemplatePopulationParamsTest {

    private final FileTemplatePopulationParamsBuilder builder = new FileTemplatePopulationParamsBuilder();
    private final FileDeleter fileDeleter = new FileDeleter();

    @Test
    public void shouldReturnOutputPath() {
        String outputPath = "test/output.txt";
        try {
            builder.setOutputPath(outputPath);

            FileTemplatePopulationParams params = builder.build();

            assertThat(params.getOutputPath()).isEqualTo(outputPath);
        } finally {
            fileDeleter.deleteFileIfExists(outputPath);
        }
    }

}
