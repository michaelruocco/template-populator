package uk.co.mruoc;

import com.google.common.collect.Maps;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DefaultTemplatePopulator implements TemplatePopulator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTemplatePopulator.class);

    @Override
    public void populate(TemplatePopulationParams params) {
        LOGGER.info("creating template with content " + params.getTemplateContent());
        JtwigTemplate template = JtwigTemplate.inlineTemplate(params.getTemplateContent());
        LOGGER.info("creating model with properties " + params.getProperties());
        JtwigModel model = JtwigModel.newModel(toMap(params.getProperties()));
        template.render(model, params.getOutputStream());
    }

    private Map<String, Object> toMap(Properties properties) {
        return new HashMap<>(Maps.fromProperties(properties));
    }

}
