package com.example.Practice14;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {
    @Value("${PAGE}")
    private String PAGE;

    private ArrayList<Level> levels = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();

    @GetMapping("/home")
    public @ResponseBody String home() throws IOException {
        WebClient client = WebClient.create(PAGE);
        String responseBody = client.get().retrieve().toEntity(String.class).block().getBody();
        return responseBody;
    }

    @GetMapping("/levels")
    public @ResponseBody String returnLevels() {
        StringBuilder builder = new StringBuilder();
        for (Level level : levels) {
            builder.append(level.getComplexity()).append(" ").append(level.getLevelName()).append("\n");
        }
        return builder.toString();
    }

    @GetMapping("games")
    public @ResponseBody String returnGames() {
        StringBuilder builder = new StringBuilder();
        for (Game game : games) {
            builder.append(game.getLevel().getLevelName()).append(" ")
                    .append(game.getLevel().getComplexity()).append(" ")
                    .append("\n");
        }
        return builder.toString();
    }

    @PutMapping("/levels/add")
    public @ResponseBody String addLevel(@RequestParam String complexity,
                                        @RequestParam String levelName) {
        if (findLevel(levelName) != -1) {
            return "Level with the same name is already exists";
        }
        levels.add(new Level(complexity, levelName));
        return "Success!";
    }

    @PutMapping("/games/add")
    public @ResponseBody String addGame(@RequestParam String complexity,
                                        @RequestParam String levelName,
                                        @RequestParam String name,
                                        @RequestParam String creationDate) {
        Level level = new Level(complexity, levelName);
        if (findGame(name, level, creationDate) != -1) {
            return "The same game is already exists";
        }
        games.add(new Game(name, creationDate, level));
        return "Success!";
    }

    @DeleteMapping(value = "/levels/delete")
    public @ResponseBody String removeLevel(@RequestParam String name) {
        int id = findLevel(name);
        if (id == -1) {
            return "There is no level with that name";
        }
        levels.remove(id);
        return "Success";
    }

    @DeleteMapping("/games/delete")
    public @ResponseBody String removeGame(@RequestParam String complexity,
                                            @RequestParam String levelName,
                                            @RequestParam String name,
                                            @RequestParam String creationDate) {
        Level level = new Level(complexity, levelName);
        int id = findGame(name, level, creationDate);
        if (id == -1) {
            return "The is no such an game";
        }
        games.remove(id);
        return "Success!";
    }

    private int findLevel(String levelName) {
        for (int i = 0; i < levels.size(); ++i) {
            if (levels.get(i).getLevelName().equals(levelName))
                return i;
        }
        return -1;
    }

    private int findGame(String name, Level level, String creationDate) {
        for (int i = 0; i < games.size(); ++i) {
            if (games.get(i).getLevel().equals(level) && games.get(i).getCreationDate().equals(creationDate))
                return i;
        }
        return -1;
    }
}