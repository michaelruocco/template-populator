package uk.co.mruoc;

import uk.co.mruoc.properties.FileContentLoader;
import uk.co.mruoc.properties.FileSystemFileContentLoader;
import uk.co.mruoc.properties.FileSystemPropertyLoader;
import uk.co.mruoc.properties.PropertyLoader;

import java.io.OutputStream;
import java.util.Properties;

public class FileTemplatePopulationParams implements TemplatePopulationParams {

    private final String templateContent;
    private final Properties properties;
    private final OutputStream outputStream;
    private final String outputPath;

    public FileTemplatePopulationParams(FileTemplatePopulationParamsBuilder builder) {
        this.templateContent = builder.templateContent;
        this.properties = builder.properties;
        this.outputStream = builder.outputStream;
        this.outputPath = builder.outputPath;
    }

    @Override
    public String getTemplateContent() {
        return templateContent;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public static class FileTemplatePopulationParamsBuilder {

        private final FileContentLoader contentLoader = new FileSystemFileContentLoader();
        private final PropertyLoader propertyLoader = new FileSystemPropertyLoader();
        private final FileOutputStreamConverter fileOutputStreamBuilder = new FileOutputStreamConverter();

        private String templateContent;
        private Properties properties;
        private OutputStream outputStream;
        private String outputPath;

        public FileTemplatePopulationParamsBuilder setTemplatePath(String templatePath) {
            this.templateContent = contentLoader.loadContent(templatePath);
            return this;
        }

        public FileTemplatePopulationParamsBuilder setPropertiesPath(String propertiesPath) {
            this.properties = propertyLoader.load(propertiesPath);
            return this;
        }

        public FileTemplatePopulationParamsBuilder setOutputPath(String outputPath) {
            this.outputPath = outputPath;
            this.outputStream = fileOutputStreamBuilder.toOutputStream(outputPath);
            return this;
        }

        public FileTemplatePopulationParams build() {
            return new FileTemplatePopulationParams(this);
        }

    }

}
