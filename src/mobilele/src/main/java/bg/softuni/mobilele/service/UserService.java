package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.model.dto.UserRegisterDTO;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

//    boolean login(UserLoginDTO loginDTO);

//    void login(UserEntity userEntity);

    boolean loginUser(UserLoginDTO userLoginDTO);

    void logoutUser();
}
