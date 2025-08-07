package com.TestRestTemplatePOC;

import com.TestRestTemplatePOC.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //it will avoid from conflict of port
public class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getUrl(String path) {
        return "http://localhost:" + port + path; //method uses this injected port to build full request URLs like http://localhost:123/employees
    }

    @Test
    public void testCreateAndFetchEmployee() {
        Employee employee = new Employee(null, "Saddam", "IT");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

        // POST
        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getUrl("/employees"), request, Employee.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());//Ensures the server responded with a 200 OK, meaning the request was successfull
        assertNotNull(postResponse.getBody());//Confirms that the response contains a body (i.e., an Employee object was returned)
        assertNotNull(postResponse.getBody().getId());

        // GET
        Long id = postResponse.getBody().getId();
        ResponseEntity<Employee> getResponse = restTemplate.getForEntity(getUrl("/employees/" + id), Employee.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Saddam", getResponse.getBody().getName());
    }
}