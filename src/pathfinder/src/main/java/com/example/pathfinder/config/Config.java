package com.example.pathfinder.config;

import com.example.pathfinder.model.Category;
import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.binding.AddRouteDTO;
import com.example.pathfinder.model.dto.binding.UserRegistrationDTO;
import com.example.pathfinder.model.dto.view.RouteDetailsViewDTO;
import com.example.pathfinder.model.dto.view.RouteViewDTO;
import com.example.pathfinder.model.enums.CategoryName;
import com.example.pathfinder.model.enums.Level;
import com.example.pathfinder.model.enums.UserRole;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.repository.RoleRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.session.LoggedUser;
import com.example.pathfinder.util.YouTubeUtil;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class Config {
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RoleRepository roleRepository;

    public Config(LoggedUser loggedUser, UserRepository userRepository, CategoryRepository categoryRepository, RoleRepository roleRepository) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        //AddRouteDTO -> Route
        Provider<User> loggedUserProvider = req -> getLoggedUser();
        Provider<String> youTubeSubUrlProvider = req -> YouTubeUtil.getURL((String) req.getSource());

        Converter<Set<CategoryName>, Set<Category>> toEntitySet =
                ctx -> ctx.getSource() == null
                        ? null
                        : categoryRepository.findAllByNameIn(ctx.getSource());

        modelMapper.createTypeMap(AddRouteDTO.class, Route.class)
                .addMappings(mapper -> mapper
                        .using(toEntitySet)
                        .map(AddRouteDTO::getCategories, Route::setCategories))
                .addMappings(mapper -> mapper
                        .when(Conditions.isNull())
                        .with(loggedUserProvider)
                        .map(AddRouteDTO::getAuthor, Route::setAuthor))
                .addMappings(mapper -> mapper
                        .with(youTubeSubUrlProvider)
                        .map(AddRouteDTO::getVideoUrl, Route::setVideoUrl));

        //UserRegistrationDTO -> User
        Provider<User> newUserProvider = req -> new User()
                .setRoles(Set.of(roleRepository.findByName(UserRole.valueOf("USER"))))
                .setLevel(Level.BEGINNER);

        Converter<String, String> passwordConverter
                = ctx -> ctx.getSource() == null
                ? null
                : this.passwordEncoder().encode(ctx.getSource());

        modelMapper
                .createTypeMap(UserRegistrationDTO.class, User.class)
                .setProvider(newUserProvider);
//                .addMappings(mapper -> mapper
//                        .using(passwordConverter)
//                        .map(UserRegistrationDTO::getPassword, User::setPassword));

        //Route -> RouteDetailsViewDTO
        modelMapper
                .createTypeMap(Route.class, RouteDetailsViewDTO.class)
                .addMappings(mapper -> mapper
                        .map(route -> route.getAuthor().getFullName(), RouteDetailsViewDTO::setAuthorName));

        return modelMapper;
    }

    private User getLoggedUser() {
        final String username = loggedUser.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User with username: " + username + " is not present"));
    }


}
