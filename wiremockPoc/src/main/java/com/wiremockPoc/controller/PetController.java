package com.wiremockPoc.controller;
import com.wiremockPoc.model.PetResponse;
import com.wiremockPoc.service.PetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    public PetResponse getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }
}
