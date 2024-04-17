package com.example.Practice12;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static FileHasher fileHasher;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		System.out.println("Запуск приложения");
		Thread.sleep(3000);
		System.out.println("Приложение завершило работу");
    }

	@Override
	public void run(String... args) throws Exception { }
}
