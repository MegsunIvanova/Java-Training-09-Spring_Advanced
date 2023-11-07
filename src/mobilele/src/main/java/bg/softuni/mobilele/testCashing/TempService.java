package bg.softuni.mobilele.testCashing;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempService {
    @Cacheable("students")
    public List<StudentDTO> findAllStudents() {
        System.out.println("Students loading ...");
        return List.of(
                new StudentDTO("Pesho", 20),
                new StudentDTO("Anna", 19)
        );
    }
}
