package bg.softuni.cacheredis.service;

import bg.softuni.cacheredis.model.StudentDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Cacheable("students")
    public List<StudentDTO> getAllStudents() {

        System.out.println("Calculating students ...");

        List<StudentDTO> allStudents = new ArrayList<>();
        allStudents.add(new StudentDTO("Pesho", 20));
        allStudents.add(new StudentDTO("Anna", 19));

        return allStudents;
    }

}
