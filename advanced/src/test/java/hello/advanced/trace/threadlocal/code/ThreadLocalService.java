package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name){
        log.info("์ ์ฅ name={} --> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("์กฐํ nameStore.get()={}", nameStore.get());
        return nameStore.get();
    }

    private void sleep(int milliMs) {
        try {
            Thread.sleep(milliMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
