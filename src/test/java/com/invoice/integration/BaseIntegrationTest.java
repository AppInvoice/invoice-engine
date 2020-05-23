package com.invoice.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base Integration Test.
 * <p>
 * This class should be used by every integration test. Instead of running as much contexts as Integration classes,
 * this will run once and will be used by the classes that extends this.
 * As the context is shared, the beans will be shared, so if you need to mock or change a bean, do NOT extends this class.
 */
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    protected String url;

    @Value("${server.servlet.context-path}")
    private String mainPath;

    @BeforeAll
    public void setUp() {
        url = "http://localhost:" + port + mainPath;
    }
}
