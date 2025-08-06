package com.wiremockPoc;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.wiremockPoc.model.PetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClient;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@WireMockTest(httpPort = 8089)
@TestPropertySource(properties = {
        "external.api.base-url=http://localhost:8089"
})
public class PetControllerTest {

    @Test
    void shouldReturnMockedPet() {
        stubFor(get(urlEqualTo("/pet/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                    "id": 1,
                                    "name": "WireDog",
                                    "status": "available"
                                }
                                """)));

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8089")
                .build();

        PetResponse pet = restClient.get()
                .uri("/pet/{id}", 1)
                .retrieve()
                .body(PetResponse.class);

        assertNotNull(pet);
        assertEquals(1L, pet.getId());
        assertEquals("WireDog", pet.getName());
        assertEquals("available", pet.getStatus());
    }
}