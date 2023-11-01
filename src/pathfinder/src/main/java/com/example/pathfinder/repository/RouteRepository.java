package com.example.pathfinder.repository;

import com.example.pathfinder.model.Category;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r ORDER BY size(r.comments) DESC LIMIT 1")
    Route findMostCommented();

    List<Route> findAllByCategories_Name(CategoryName categoryName);
}
