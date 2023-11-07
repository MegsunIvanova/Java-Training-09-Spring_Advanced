package bg.softuni.mobilele.testCashing;

import bg.softuni.mobilele.testCashing.TempService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestComponent implements CommandLineRunner {
    private final TempService tempService;

    public TestComponent(TempService tempService) {
        this.tempService = tempService;
    }

    @Override
    public void run(String... args) throws Exception {
        tempService.findAllStudents();
        tempService.findAllStudents();
        tempService.findAllStudents();
        tempService.findAllStudents();
    }
}
