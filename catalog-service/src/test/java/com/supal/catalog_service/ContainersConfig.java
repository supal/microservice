package com.supal.catalog_service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

// If we put @configuration here the bean will be used by the application which we don't want.
// That is why we are using @TestConfiguration
// By default the proxyBeanMethod is true, which will create singleton bean if other
// @Bean is being called by the method (which is the default behavior of the spring)
// However, in this case it is an overhead as we are not calling other Bean.
// If we use proxyBeanMethod = true (or if we don't use proxyBeanMethod = false)
// the test container will try to load CGLIB (code generation library)
@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {
    @Bean
    //    Traditionally, when using Testcontainers for integration tests,
    //    you had to manually configure and expose container properties
    //    (e.g., setting up database URLs, credentials, etc.).
    //    @ServiceConnection automates this by:
    //     ✅ Starting a container and registering it as a service.
    //     ✅ Updating application properties with the container's connection details.
    //     ✅ Auto-configuring dependencies (like a database or messaging system).
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:17-alpine"));
    }
}
