package com.wiremockPoc.model;

import lombok.Data;

@Data
public class PetResponse {
    private long id;
    private String name;
    private String status;
}