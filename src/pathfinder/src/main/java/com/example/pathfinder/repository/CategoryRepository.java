package com.example.pathfinder.repository;

import com.example.pathfinder.model.Category;
import com.example.pathfinder.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryName name);

    Category findFirstByName(CategoryName name);

    Set <Category> findAllByNameIn(Set<CategoryName> names);

}
