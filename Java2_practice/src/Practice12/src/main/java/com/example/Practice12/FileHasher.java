package com.example.Practice12;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Component
public class FileHasher {
    @Value("#{springApplicationArguments.nonOptionArgs[0]}")
    private String firstPath;
    @Value("#{springApplicationArguments.nonOptionArgs[1]}")
    private String secondPath;

    @PostConstruct
    private void onConstruct() {
        try {
            if (firstPath == null) {
                System.out.println("Аргументы не переданы");
            }

            else if (secondPath == null) {
                Files.writeString(Path.of(firstPath), "null\n", StandardOpenOption.CREATE);
                System.out.println("Не передан путь к новому файлу");
            }

            else {
                String oldText = Files.readString(Path.of(firstPath));
                String newText = "null\n";
                if (!oldText.isEmpty()) {
                    String hashCode = String.valueOf(oldText.hashCode());
                    newText = hashCode + "\n";
                }
                Files.writeString(Path.of(secondPath), newText, StandardOpenOption.CREATE);
                System.out.println("Информация захеширована");
            }
        } catch (NoSuchFileException e) {
            System.out.println("Файл не найден: " + e.getFile());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @PreDestroy
    private void preDestroy() {
        try {
            if (firstPath != null && secondPath != null) {
                Files.delete(Path.of(firstPath));
                System.out.println("Старый файл удален");
            }
        } catch (NoSuchFileException e) {
            System.out.println("Файл для удаления не найден: " + e.getFile());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
