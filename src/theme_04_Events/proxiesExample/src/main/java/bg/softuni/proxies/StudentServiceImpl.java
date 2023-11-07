package bg.softuni.proxies;

import java.util.List;

public class StudentServiceImpl implements StudentServiceIfc {
    @Override
    @Cacheable("students")
    public List<StudentDTO> getAllStudents() {
        System.out.println("CALCULATING ALL STUDENTS");

        return List.of(
                new StudentDTO("Pesho", 20),
                new StudentDTO("Anna", 19)
        );
    }
}
