package uk.co.mruoc.template;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTemplatePopulator implements TemplatePopulator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTemplatePopulator.class);

    @Override
    public void populate(TemplatePopulationParams params) {
        try {
            doPopulate(params);
        } catch (IOException e) {
            throw new TemplatePopulationException(e);
        }
    }

    private void doPopulate(TemplatePopulationParams params) throws IOException {
        OutputStream outputStream = params.getOutputStream();
        try {
            LOGGER.info("creating template with content " + params.getTemplateContent());
            JtwigTemplate template = JtwigTemplate.inlineTemplate(params.getTemplateContent());
            LOGGER.info("creating model with properties " + params.getProperties());
            JtwigModel model = JtwigModel.newModel(toMap(params.getProperties()));
            template.render(model, outputStream);
        } finally {
            outputStream.close();
        }
    }

    private Map<String, Object> toMap(Properties properties) {
        return new HashMap<>(Maps.fromProperties(properties));
    }

}
