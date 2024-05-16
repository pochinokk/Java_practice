package com.example.Practice24.service;

import com.example.Practice24.entity.Game;
import com.example.Practice24.entity.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@ManagedResource(objectName = "com.example:type=EntitySaveService")
public class EntitySaveService {
    @Autowired
    private LevelService levelService;
    @Autowired
    private GameService gameService;

    private static final Logger log = LoggerFactory.getLogger(EntitySaveService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private void makeLevelsBackup() {
        List<Level> levels = levelService.readAll();
        try {
            RandomAccessFile writer = new RandomAccessFile("backup\\levels.txt", "rw");
            writer.setLength(0);
            for (Level level : levels) {
                String levelStr = String.format("%d %s %s %d\n",
                        level.getId(),
                        level.getLevelName(),
                        level.getComplexity(),
                        level.getGame().getId());
                writer.write(levelStr.getBytes());
            }
            writer.close();
        } catch (IOException e) {
            log.error("Error occurred while writing levels backup: {}", e.getMessage());
        }
    }

    private void makeGamesBackup() {
        List<Game> games = gameService.readAll();
        try {

            RandomAccessFile writer = new RandomAccessFile("backup\\games.txt", "rw");
            writer.setLength(0);
            for (Game game : games) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                String creationDateStr = dateFormat.format(game.getCreationDate());
                String gameStr = String.format("%d %s %s\n",
                        game.getId(),
                        creationDateStr,
                        game.getName());
                writer.write(gameStr.getBytes());
            }
            writer.close();
        } catch (IOException e) {
            log.error("Error occurred while writing games backup: {}", e.getMessage());
        }
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void makeBackup() {
        System.out.println("It is working");
        makeLevelsBackup();
        makeGamesBackup();
        log.info("Backups are made: {}", dateFormat.format(new Date()));
    }
}
