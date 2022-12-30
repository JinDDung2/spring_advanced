package hello.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(CallBack callBack) {
        long startTime = System.currentTimeMillis();
        // 비지니스 로직
        callBack.call(); // 위임
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("비지니스 로직 종료 | 수행시간={}Ms", resultTime);
    }
}
