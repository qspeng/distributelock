package me.qspeng.distributelock.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoTest {
    @Autowired
    NormalCase normalCase;

    @Autowired
    OneByOneCase oneByOneCase;

    @Autowired
    LockTimeoutCase lockTimeoutCase;

    @Autowired
    WatchDogCase watchDogCase;

    @Autowired
    BusinessUseCase businessUseCase;

    @Autowired
    MultiThreadCase multiThreadCase;

    @Test
    void should_only_one_operation_get_lock() {
        normalCase.run();
    }

    @Test
    void should_get_lock_one_by_one() {
        oneByOneCase.run();
    }

    @Test
    void should_get_lock_after_timeout() throws InterruptedException {
        lockTimeoutCase.run();
    }

    @Test
    void should_not_get_lock_after_timeout_with_watch_dog() throws InterruptedException {
        watchDogCase.run();
    }

    @Test
    void should_get_lock_when_business_release_the_lock_() throws InterruptedException {
        watchDogCase.runWithUnlock();
    }

    @Test
    void should_release_the_lock_with_happy_path() {
        businessUseCase.happyPath();
    }

    @Test
    void should_release_the_lock_with_error_path() {
        businessUseCase.errorPath();
    }

    @Test
    void should_only_one_thread_get_the_lock_for_same_identifiers() throws InterruptedException {
        multiThreadCase.run();
    }
}
