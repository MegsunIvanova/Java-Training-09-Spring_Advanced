package bg.softuni.mobilele.model.dto;

public record UserLoginDTO(String email, String password) {
    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "email='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }
}
