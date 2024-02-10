package com.example.rest.rest.configuration;

//import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApiDescription(){
        Server localHostServer = new Server();
        localHostServer.setUrl("http://localhost:8080");
        localHostServer.setDescription("Local env");

        Server prodServer = new Server();
        prodServer.setUrl("http://some.prod.url");
        prodServer.setDescription("Prod env");

        Contact contact = new Contact();
        contact.setName("Vova");
        contact.setEmail("email");
        contact.setUrl("http://some.url");

        License mitLicence = new License().name("GNU AGPLv3")
                .url("http://some.url");

        Info info = new Info()
                .title("Client API")
                .version("1.0")
                .contact(contact)
                .description("API for client orders")
                .termsOfService("http://some.url")
                .license(mitLicence);

        return new OpenAPI().info(info).servers(List.of(localHostServer, prodServer));
    }
}
