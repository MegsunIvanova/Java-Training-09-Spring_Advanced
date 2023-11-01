package com.example.pathfinder.service.impl;

import com.example.pathfinder.exeptions.RouteNotFoundException;
import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.binding.AddRouteDTO;
import com.example.pathfinder.model.dto.binding.UploadPictureRouteDTO;
import com.example.pathfinder.model.dto.view.RouteCategoryViewDTO;
import com.example.pathfinder.model.dto.view.RouteDetailsViewDTO;
import com.example.pathfinder.model.dto.view.RouteViewDTO;
import com.example.pathfinder.model.enums.CategoryName;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RouteServiceImpl implements RouteService {
    private static final String BASE_GPX_COORDINATES_PATH = ".\\src\\main\\resources\\coordinates\\";
    private static final String BASE_IMAGES_PATH = ".\\src\\main\\resources\\images\\";
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public RouteServiceImpl(RouteRepository routeRepository, UserRepository userRepository, PictureRepository pictureRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public Route getMostCommented() {
        return this.routeRepository.findMostCommented();
    }

    @Override
    public void add(AddRouteDTO routeDTO) {
        Route route = modelMapper.map(routeDTO, Route.class);

        String filePath = getFilePath(routeDTO.getName());
        boolean isUploaded = uploadGpxCoordinates(routeDTO.getGpxCoordinates(), filePath);
        if (isUploaded) {
            route.setGpxCoordinates(filePath);
        }

        routeRepository.save(route);
    }

    private boolean uploadGpxCoordinates(MultipartFile file, String filePath) {
        try {
            File newfile = new File(filePath);
            newfile.getParentFile().mkdirs();
            newfile.createNewFile();

            OutputStream outputStream = new FileOutputStream(newfile);
            outputStream.write(file.getBytes());

            return true;

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<RouteViewDTO> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(this::getRouteObjectFunction)
                .toList();
    }

    @Override
    public RouteDetailsViewDTO getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found!"));

        return modelMapper.map(route, RouteDetailsViewDTO.class);
    }

    @Override
    public void uploadPicture(UploadPictureRouteDTO uploadPictureRouteDTO) {
        MultipartFile pictureFile = uploadPictureRouteDTO.getPicture();
        String picturePath = getPicturePath(pictureFile);
        try {
            File file = new File(picturePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(pictureFile.getBytes());

            Optional<Route> optRoute = routeRepository.findById(uploadPictureRouteDTO.getId());
            Optional<User> optUser = userRepository.findByUsername(loggedUser.getUsername());

            if (optRoute.isPresent() && optUser.isPresent()) {
                Route route = optRoute.get();
                User author = optUser.get();

                Picture picture = new Picture()
                        .setRoute(route)
                        .setAuthor(author)
                        .setUrl(picturePath)
                        .setTitle(uploadPictureRouteDTO.getTitle());

                Picture saved = pictureRepository.save(picture);
                route.getPictures().add(saved);
                routeRepository.save(route);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<RouteCategoryViewDTO> getAllByCategory(CategoryName categoryName) {
        List<Route> routes = routeRepository.findAllByCategories_Name(categoryName);

        return routes.stream()
                .map(route -> modelMapper.map(route, RouteCategoryViewDTO.class))
                .toList();
    }

    private RouteViewDTO getRouteObjectFunction(Route route) {
        RouteViewDTO routeView = modelMapper.map(route, RouteViewDTO.class);
        Picture picture = route.getPictures()
                .stream()
                .findFirst()
                .orElse(null);

        routeView.setImageUrl(picture == null
                ? null
                : picture.getUrl());

        return routeView;
    }

    private String getFilePath(String routName) {
        String pathPattern = "%s%s\\%s_%s.xml";
        return String.format(pathPattern,
                BASE_GPX_COORDINATES_PATH,
                loggedUser.getUsername(),
                transformRouteName(routName),
                UUID.randomUUID());
    }

    private String getPicturePath(MultipartFile pictureFile) {
        String[] splitPictureName = pictureFile.getOriginalFilename().split("\\.");
        String ext = splitPictureName[splitPictureName.length - 1];

        String pathPattern = "%s%s\\%s.%s";
        return String.format(pathPattern,
                BASE_IMAGES_PATH,
                loggedUser.getUsername(),
                UUID.randomUUID(),
                ext);
    }

    private String transformRouteName(String routeName) {
        return routeName.toLowerCase()
                .replaceAll("\\s+", "_");
    }


}
