package com.example.Student.Management.service;

import com.example.Student.Management.dto.StudentRequest;
import com.example.Student.Management.dto.StudentResponse;
import com.example.Student.Management.model.Student;
import com.example.Student.Management.exception.BadRequestException;
import com.example.Student.Management.exception.ResourceNotFoundException;
import com.example.Student.Management.repo.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    private StudentResponse toResponse(Student s) {
        return new StudentResponse(
                s.getId(),
                s.getRollNumber(),
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getDepartment(),
                s.getYear(),
                s.getGpa()
        );
    }

    private Student toEntity(StudentRequest req) {
        return new Student(
                req.getRollNumber(),
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                req.getDepartment(),
                req.getYear(),
                req.getGpa()
        );
    }

    public Page<StudentResponse> getAll(int page, int size, String department) {
        Pageable p = PageRequest.of(Math.max(0, page), Math.max(1, size), Sort.by("id").ascending());
        Page<Student> pg = repo.findAll(p);

        if (department == null || department.isBlank()) {
            return pg.map(this::toResponse);
        }

        List<StudentResponse> filtered = pg.stream()
                .filter(s -> department.equalsIgnoreCase(Optional.ofNullable(s.getDepartment()).orElse("")))
                .map(this::toResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(filtered, p, filtered.size());
    }

    public StudentResponse getById(Long id) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
        return toResponse(s);
    }

    @Transactional
    public StudentResponse create(StudentRequest req) {
        if (req.getRollNumber() == null || req.getRollNumber().isBlank()) {
            throw new BadRequestException("rollNumber is required");
        }
        if (repo.existsByRollNumber(req.getRollNumber())) {
            throw new BadRequestException("rollNumber already exists");
        }
        if (req.getEmail() != null && !req.getEmail().isBlank() && repo.existsByEmail(req.getEmail())) {
            throw new BadRequestException("email already exists");
        }
        Student saved = repo.save(toEntity(req));
        return toResponse(saved);
    }

    @Transactional
    public StudentResponse update(Long id, StudentRequest req) {
        Student existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        if (!Objects.equals(existing.getRollNumber(), req.getRollNumber())
                && repo.existsByRollNumber(req.getRollNumber())) {
            throw new BadRequestException("rollNumber already exists");
        }

        if (req.getEmail() != null && !Objects.equals(req.getEmail(), existing.getEmail())
                && repo.existsByEmail(req.getEmail())) {
            throw new BadRequestException("email already exists");
        }

        existing.setRollNumber(req.getRollNumber());
        existing.setFirstName(req.getFirstName());
        existing.setLastName(req.getLastName());
        existing.setEmail(req.getEmail());
        existing.setDepartment(req.getDepartment());
        existing.setYear(req.getYear());
        existing.setGpa(req.getGpa());

        Student saved = repo.save(existing);
        return toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id " + id);
        }
        repo.deleteById(id);
    }

    public List<StudentResponse> getAllNoPage() {
        return repo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

}

