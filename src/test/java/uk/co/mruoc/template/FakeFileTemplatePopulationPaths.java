package uk.co.mruoc.template;

public class FakeFileTemplatePopulationPaths implements FileTemplatePopulationPaths {

    @Override
    public String getTemplatePath() {
        return "test/template.txt";
    }

    @Override
    public String getPropertiesPath() {
        return "test/properties.properties";
    }

    @Override
    public String getOutputPath() {
        return "test/output.txt";
    }

}
