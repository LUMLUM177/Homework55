package ru.hogwarts.school.service;

import java.util.*;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Collection<Student> findStudentsByFaculty(long id) {
        return facultyRepository.findById(id).get().getStudents();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }


    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> findByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Optional<String> getLongestNameFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(faculty -> faculty.getName())
                .reduce((f1, f2) -> f1.length() > f2.length() ? f1 : f2);
    }

    public Integer calculateSum() {
        return Stream
                .iterate(1, a -> a + 1)
                .limit(10_000_000_0)
                .reduce(0, (a, b) -> a + b);
    }

    public Integer fastCalculateSum() {
        return Stream
                .iterate(1, a -> a + 1)
                .limit(10_000_000_0)
                .parallel()
                .reduce(0, (a, b) -> a + b);
    }
}