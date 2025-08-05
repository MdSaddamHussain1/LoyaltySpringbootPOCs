package com.testcontainerpoc.repository;

import com.testcontainerpoc.entity.TestContainerPoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestContainerPocRepository extends JpaRepository<TestContainerPoc, Long> {
}