package com.testcontainerpoc.service;

import com.testcontainerpoc.entity.TestContainerPoc;
import com.testcontainerpoc.repository.TestContainerPocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestContainerPocService {

    @Autowired
    private TestContainerPocRepository repository;

    public List<TestContainerPoc> getAll() {
        return repository.findAll();
    }

    public TestContainerPoc save(TestContainerPoc obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TestContainerPoc update(Long id, TestContainerPoc updated) {
        updated.setId(id);
        return repository.save(updated);
    }
}