package uk.co.mruoc.template;

import java.util.Properties;

import uk.co.mruoc.properties.FileContentLoader;
import uk.co.mruoc.properties.FileSystemFileContentLoader;
import uk.co.mruoc.properties.FileSystemPropertyLoader;
import uk.co.mruoc.properties.PropertyLoader;

public class FileTemplatePopulationParams implements TemplatePopulationParams {

    private final String templateContent;
    private final Properties properties;
    private final String outputPath;

    public FileTemplatePopulationParams(FileTemplatePopulationParamsBuilder builder) {
        this.templateContent = builder.templateContent;
        this.properties = builder.properties;
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
    public String getOutputPath() {
        return outputPath;
    }

    public static class FileTemplatePopulationParamsBuilder {

        private final FileContentLoader contentLoader;
        private final PropertyLoader propertyLoader;

        private String templateContent;
        private Properties properties;
        private String outputPath;

        public FileTemplatePopulationParamsBuilder() {
            this(new FileSystemFileContentLoader(), new FileSystemPropertyLoader());
        }

        public FileTemplatePopulationParamsBuilder(FileContentLoader contentLoader, PropertyLoader propertyLoader) {
            this.contentLoader = contentLoader;
            this.propertyLoader = propertyLoader;
        }


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
            return this;
        }

        public FileTemplatePopulationParams build() {
            return new FileTemplatePopulationParams(this);
        }

    }

}
