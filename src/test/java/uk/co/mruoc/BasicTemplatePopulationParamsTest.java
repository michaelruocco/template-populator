package uk.co.mruoc;

import org.junit.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.BasicTemplatePopulationParams.*;

public class BasicTemplatePopulationParamsTest {

    private final BasicTemplatePopulationParamsBuilder builder = new BasicTemplatePopulationParamsBuilder();

    @Test
    public void shouldReturnEmptyPropertiesByDefault() {
        TemplatePopulationParams params = builder.build();

        assertThat(params.getProperties().propertyNames().hasMoreElements()).isFalse();
    }

    @Test
    public void shouldPopulateProperties() {
        Properties properties = new Properties();

        TemplatePopulationParams params = builder
                .setProperties(properties)
                .build();

        assertThat(params.getProperties()).isEqualTo(properties);
    }

}
