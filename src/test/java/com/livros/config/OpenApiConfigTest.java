package com.livros.config;

import org.junit.jupiter.api.Test;
import org.springdoc.core.models.GroupedOpenApi;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenApiConfigTest {

	@Test
	public void testPublicApi() {

		OpenApiConfig openApiConfig = new OpenApiConfig();

		GroupedOpenApi groupedOpenApi = openApiConfig.publicApi();

		assertNotNull(groupedOpenApi);
		assertNotNull(groupedOpenApi.getGroup());
		assertNotNull(groupedOpenApi.getPathsToMatch());
	}
}
