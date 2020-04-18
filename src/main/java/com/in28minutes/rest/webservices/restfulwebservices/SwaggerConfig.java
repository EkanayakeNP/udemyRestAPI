package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.HashSet;
import java.util.*;

import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Nishantha", "", "");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Rest documentation", "Rest decsription", "1.0",
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	private static final Set<String> DEFUALT_PRODUCER_AND_CONSUMER = new HashSet<String>();

	@Bean
	public Docket api() {
		addValues();
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFUALT_PRODUCER_AND_CONSUMER)
				.consumes(DEFUALT_PRODUCER_AND_CONSUMER);
	}

	private void addValues() {
		DEFUALT_PRODUCER_AND_CONSUMER.add("application/json");
		DEFUALT_PRODUCER_AND_CONSUMER.add("application/xml");

	}
}
