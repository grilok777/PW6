package com.asynhrone.PW6;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Component
class AsyncTasks {

    private final Instant startTime;
    private final Random random = new Random();
    private final AtomicInteger scheduledTaskCount = new AtomicInteger(0);
    private final AtomicInteger asyncTaskCount = new AtomicInteger(0);
    private boolean canExecuteAsync = true;
    private boolean scheduledTaskActive = false;

    public AsyncTasks() {
        this.startTime = Instant.now();
    }

    public void startTasks() {
        scheduledTaskActive = true;
    }

    public void executeScheduledTaskImmediately() {
        if (scheduledTaskCount.get() < 4) {
            //System.out.println("Виконується запланована задача (вручну), час від запуску: "
             //       + Duration.between(startTime, Instant.now()).toSeconds() + " секунд.");
            scheduledTaskCount.incrementAndGet();
        }
    }

    public void executeRandomTaskInitial() {
        CompletableFuture.runAsync(() -> {
            try {
                while (canExecuteAsync && asyncTaskCount.get() < 4) {
                    int delay = random.nextInt(10) + 1;
                    Thread.sleep(delay * 1000L);
                    System.out.println("Виконується асинхронна задача з випадковим інтервалом, час від запуску: "
                            + Duration.between(startTime, Instant.now()).toSeconds() + " секунд.");
                    asyncTaskCount.incrementAndGet();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Асинхронну задачу перервано.");
            }
            canExecuteAsync = false;
        });
    }

    @org.springframework.scheduling.annotation.Scheduled(fixedRate = 10000)
    private void scheduledTask() {
        if (scheduledTaskActive && scheduledTaskCount.get() < 4) {
            System.out.println("Виконується запланована задача кожні 10 секунд, час від запуску: "
                    + Duration.between(startTime, Instant.now()).toSeconds() + " секунд.");
            scheduledTaskCount.incrementAndGet();
        }
        if (scheduledTaskCount.get() == 4) {
            scheduledTaskActive = false;
        }
    }

    public AtomicInteger getAsyncTaskCount() {
        return asyncTaskCount;
    }

    public AtomicInteger getScheduledTaskCount() {
        return scheduledTaskCount;
    }
}
