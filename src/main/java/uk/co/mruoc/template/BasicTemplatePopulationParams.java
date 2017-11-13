package uk.co.mruoc.template;

import java.util.Properties;

public class BasicTemplatePopulationParams implements TemplatePopulationParams {

    private final String templateContent;
    private final Properties properties;
    private final String outputPath;

    public BasicTemplatePopulationParams(BasicTemplatePopulationParamsBuilder builder) {
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

    public static class BasicTemplatePopulationParamsBuilder {

        private String templateContent;
        private Properties properties = new Properties();
        private String outputPath;

        public BasicTemplatePopulationParamsBuilder setTemplateContent(String templateContent) {
            this.templateContent = templateContent;
            return this;
        }

        public BasicTemplatePopulationParamsBuilder setProperties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public BasicTemplatePopulationParamsBuilder setProperty(String key, String value) {
            this.properties.setProperty(key, value);
            return this;
        }

        public BasicTemplatePopulationParamsBuilder setOutputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public BasicTemplatePopulationParams build() {
            return new BasicTemplatePopulationParams(this);
        }

    }

}
