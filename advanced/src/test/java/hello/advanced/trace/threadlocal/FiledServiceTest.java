package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FiledService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FiledServiceTest {

    FiledService filedService = new FiledService();

    @Test
    void field_동시성문제_없음() {
        log.info("main start");
        Runnable userA = () -> {
            filedService.logic("userA");
        };

        Runnable userB = () -> {
            filedService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(2000); // 동시성 문제가 일어나지 않도록
        threadB.start();
        sleep(3000); // 메인 쓰레드 종료 대기
        log.info("main exit");

    }

    @Test
    void field_동시성문제_발생() {
        log.info("main start");
        Runnable userA = () -> {
            filedService.logic("userA");
        };

        Runnable userB = () -> {
            filedService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100); // 동시성 문제가 일어남
        threadB.start();
        sleep(3000); // 메인 쓰레드 종료 대기
        log.info("main exit");

    }

    private void sleep(int milliMs) {
        try {
            Thread.sleep(milliMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
