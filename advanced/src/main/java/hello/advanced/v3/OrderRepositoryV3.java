package hello.advanced.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.request()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("ex 예외발생");
            }
            // 상품 저장에 1초
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millSecond) {
        try {
            Thread.sleep(millSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
