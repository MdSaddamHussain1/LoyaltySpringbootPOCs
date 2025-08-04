package com.CRUDwithJUnit.service;

import com.CRUDwithJUnit.entity.Student;
import com.CRUDwithJUnit.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository repo;

    @InjectMocks
    private StudentService service;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1L);
        student.setFirstName("saddam");
        student.setLastName("hussain");
        student.setDepartment("IT");
    }

    @Test
    void testCreateStudent() {
        when(repo.save(any(Student.class))).thenReturn(student);

        Student result = service.createStudent(student);

        assertNotNull(result);
        assertEquals("saddam", result.getFirstName());
        verify(repo, times(1)).save(student);
    }

    @Test
    void testGetAllStudents() {
        when(repo.findAll()).thenReturn(List.of(student));

        List<Student> students = service.getAllStudents();

        assertEquals(1, students.size());
        assertEquals("saddam", students.get(0).getFirstName());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGetStudentById_Found() {
        when(repo.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> found = service.getStudentById(1L);

        assertTrue(found.isPresent());
        assertEquals("hussain", found.get().getLastName());
        verify(repo, times(1)).findById(1L);
    }

    @Test
    void testGetStudentById_NotFound() {
        when(repo.findById(2L)).thenReturn(Optional.empty());

        Optional<Student> found = service.getStudentById(2L);

        assertFalse(found.isPresent());
        verify(repo, times(1)).findById(2L);
    }

    @Test
    void testUpdateStudent() {
        Student updated = new Student();
        updated.setFirstName("sadd");
        updated.setLastName("zaman");
        updated.setDepartment("BTech");

        when(repo.findById(1L)).thenReturn(Optional.of(student));
        when(repo.save(any(Student.class))).thenReturn(updated);

        Student result = service.updateStudent(1L, updated);

        assertEquals("sadd", result.getFirstName());
        assertEquals("zaman", result.getLastName());
        assertEquals("BTech", result.getDepartment());
        verify(repo, times(1)).findById(1L);
        verify(repo, times(1)).save(student);
    }

    @Test
    void testUpdateStudent_NotFound() {
        when(repo.findById(2L)).thenReturn(Optional.empty());

        Student updated = new Student();
        updated.setFirstName("saddam");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.updateStudent(2L, updated);
        });

        assertEquals("Not found", exception.getMessage());
        verify(repo, times(1)).findById(2L);
        verify(repo, never()).save(any());
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(repo).deleteById(1L);

        service.deleteStudent(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}