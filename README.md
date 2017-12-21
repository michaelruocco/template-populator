# Template Populator

[![Build Status](https://travis-ci.org/michaelruocco/template-populator.svg?branch=master)](https://travis-ci.org/michaelruocco/template-populator)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/template-populator/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/template-populator?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/46f9bf471317401d938cf3c39ee94b75)](https://www.codacy.com/app/michaelruocco/template-populator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/template-populator&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/template-populator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/template-populator)

This library provides functionality to easily build files using a template and a
property file to load replacement values. The primary intended use case is around building
configuration files for various environments. In many cases you might have a standard template
that oulines the format of the config, but you will want to substitute different values into
the structure depending on the environment it is intended to be used in.

## Usage

To use the library from a program you will need to add a dependency to your project. In gradle you would do this by
adding the following to your build.gradle file:

```
dependencies {
    compile 'com.github.michaelruocco:tepmlate-populator:3.0.4'
}
```

Once you have the dependency in you need to build your template population config, a simple
example of this using files from the local file system is shown below:

```
TemplatePopulationParams params = new FileTemplatePopulationParamsBuilder()
        .setTemplatePath("test/template.txt")
        .setPropertiesPath("test/properties.properties")
        .setOutputPath("test/output.txt")
        .build();

TemplatePopulator populator = new DefaultTemplatePopulator();
populator.populate(params);
```

In the above example, given the test/template.txt file contains:

```
my file {{var1}} {{var2}}
```

And the properties file at test/properties.properties contains:

```
var1=testing
var2=templates
```

The the output file produced at test/output.txt will contain:

```
my file testing templates
```

There are various TemplatePopulationParams implementations but the most common use case is to use the
FileTemplatePopulationParams as demonstrated above.

## Running the Tests

You can run the tests for this project by running the following command:

```
gradlew clean build
```

## Checking dependencies

You can check the current dependencies used by the project to see whether
or not they are currently up to date by running the following command:

```
gradlew dependencyUpdates
```