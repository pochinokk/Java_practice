package com.example.Practice19.repository;

import com.example.Practice19.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
    List<Level> findAllByComplexity(String complexity);
    List<Level> findAllByLevelName(String levelName);
    List<Level> findAllByComplexityAndLevelName(String complexity, String levelName);
}
