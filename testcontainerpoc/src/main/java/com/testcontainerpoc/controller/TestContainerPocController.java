package com.testcontainerpoc.controller;

import com.testcontainerpoc.entity.TestContainerPoc;
import com.testcontainerpoc.service.TestContainerPocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestContainerPocController {

    @Autowired
    private TestContainerPocService service;

    @GetMapping
    public List<TestContainerPoc> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TestContainerPoc save(@RequestBody TestContainerPoc obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public TestContainerPoc update(@PathVariable Long id, @RequestBody TestContainerPoc obj) {
        return service.update(id, obj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}