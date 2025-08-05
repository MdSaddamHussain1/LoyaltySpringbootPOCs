package com.testcontainerpoc;

import com.testcontainerpoc.entity.TestContainerPoc;
import com.testcontainerpoc.repository.TestContainerPocRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class TestContainerPocIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("SpringbootOperation")
            .withUsername("postgres")
            .withPassword("manage");

    @Autowired
    private TestContainerPocRepository repository;

    @DynamicPropertySource
    static void setDatasourceProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl); //:: is use for methods reference
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setup() {
        repository.deleteAll();
        repository.save(new TestContainerPoc(null, "Record1", "First test record"));
        repository.save(new TestContainerPoc(null, "Record2", "Second test record"));
        repository.save(new TestContainerPoc(null, "Record3", "Third test record"));
    }

    @Test
    void shouldReturnAllRecords() {
        List<TestContainerPoc> list = repository.findAll();
        assertEquals(3, list.size());
    }
}