package com.example.Practice15.controller;

import com.example.Practice15.entity.Level;
import com.example.Practice15.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LevelController {
    @Autowired
    private LevelService levelService;

    @GetMapping(path = "/levels")
    public @ResponseBody ResponseEntity getLevels() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(levelService.readAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/levels/create")
    public ResponseEntity createLevel(@RequestBody Level level) {
        try {

            if (level == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Level data is empty");
            }

            levelService.create(level);
            return ResponseEntity.status(HttpStatus.CREATED).body("Level was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping(path = "/levels/delete")
    public @ResponseBody ResponseEntity deleteLevel(int id) {
        try {
            if (levelService.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Level was deleted");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleting");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
