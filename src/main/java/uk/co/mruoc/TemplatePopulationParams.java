package uk.co.mruoc;

import java.io.OutputStream;
import java.util.Properties;

public interface TemplatePopulationParams {

    String getTemplateContent();

    Properties getProperties();

    OutputStream getOutputStream();

}
