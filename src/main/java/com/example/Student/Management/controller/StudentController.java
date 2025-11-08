package com.example.Student.Management.controller;

import com.example.Student.Management.model.Student;
import com.example.Student.Management.repo.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Student> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Student s) {
        try {
            Student saved = repo.save(s);
            return ResponseEntity.created(URI.create("/api/students/" + saved.getId())).body(saved);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body("Duplicate rollNumber or email or constraint violated.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Student s) {
        return repo.findById(id).map(existing -> {
            existing.setRollNumber(s.getRollNumber());
            existing.setFirstName(s.getFirstName());
            existing.setLastName(s.getLastName());
            existing.setEmail(s.getEmail());
            existing.setDepartment(s.getDepartment());
            existing.setYear(s.getYear());
            try {
                Student updated = repo.save(existing);
                return ResponseEntity.ok(updated);
            } catch (DataIntegrityViolationException ex) {
                return ResponseEntity.badRequest().body("Duplicate rollNumber or email.");
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

