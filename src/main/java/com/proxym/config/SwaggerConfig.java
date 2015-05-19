package com.proxym.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

/**
 * This class is a java base configuration for swagger-ui, it can be user by
 * the rest controllers to write a useful documentation.
 * 
 * @author Nessrine rinez
 * @version 1.0
 *
 */
@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

	/**
	 * spring config class .
	 */
    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Set up the configuration /
     * 
     * @param springSwaggerConfig {@link SpringSwaggerConfig}
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    /**
     * set up the swagger description.
     * 
     * @return {@link SwaggerSpringMvcPlugin}
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                //This info will be used in Swagger. See realisation of ApiInfo for more details.
                .apiInfo(new ApiInfo(
                        "Proxym resource API",
                        "This app is for proxym-it, resource management. It represents model of resources booking ",
                        null,
                        null,
                        null,
                        null
                ))
                //Here we disable auto generating of responses for REST-endpoints
                .useDefaultResponseMessages(false)
                //Here we specify URI patterns which will be included in Swagger docs. Use regex for this purpose.
                .includePatterns("/proxym.*");
    }

}