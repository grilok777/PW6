package com.asynhrone.PW6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;


@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Pw6Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Pw6Application.class, args);

		Scanner scanner = new Scanner(System.in);
		System.out.print("Виберіть Y/N для запуску задачі кожні 10 секунд (Y/N): ");
		String choice = scanner.nextLine().trim().toUpperCase();
		AsyncTasks asyncTasks = context.getBean(AsyncTasks.class);

		// Завжди запускаємо асинхронні задачі
		asyncTasks.executeRandomTaskInitial();

		if (choice.equals("Y")) {
			// Запуск запланованих задач
			//asyncTasks.executeScheduledTaskImmediately();
			asyncTasks.startTasks();
		} else if (choice.equals("N")) {
			System.out.println("Поточна задача пропущена.");
		} else {
			System.out.println("Некоректний вибір. Поточна задача пропущена.");
		}

		// Завершення програми після виконання відповідних задач
		CompletableFuture.runAsync(() -> {
			try {
				while (asyncTasks.getAsyncTaskCount().get() < 4 ||
						(choice.equals("Y") && asyncTasks.getScheduledTaskCount().get() < 4)) {
					Thread.sleep(100);
				}
				System.out.println("Вимкнення програми...");
				SpringApplication.exit(context, () -> 0);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});
	}
}

