package org.stroganov.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("org.stroganov")
@PropertySource("classpath:app.properties")
public class ConfigurationSpring {
    @Autowired
    Environment env;
}
