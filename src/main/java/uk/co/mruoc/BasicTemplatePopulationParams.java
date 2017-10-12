package uk.co.mruoc;

import java.io.OutputStream;
import java.util.Properties;

public class BasicTemplatePopulationParams implements TemplatePopulationParams {

    private final String templateContent;
    private final Properties properties;
    private final OutputStream outputStream;

    public BasicTemplatePopulationParams(BasicTemplatePopulationParamsBuilder builder) {
        this.templateContent = builder.templateContent;
        this.properties = builder.properties;
        this.outputStream = builder.outputStream;
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

    public static class BasicTemplatePopulationParamsBuilder {

        private String templateContent;
        private Properties properties = new Properties();
        private OutputStream outputStream;

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

        public BasicTemplatePopulationParamsBuilder setOutputStream(OutputStream outputStream) {
            this.outputStream = outputStream;
            return this;
        }

        public TemplatePopulationParams build() {
            return new BasicTemplatePopulationParams(this);
        }

    }

}
