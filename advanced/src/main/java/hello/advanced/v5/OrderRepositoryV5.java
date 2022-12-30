package hello.advanced.v5;

import hello.advanced.trace.callback.TraceCallBack;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void save(String itemId) {
        traceTemplate.execute("OrderRepository.request()", (TraceCallBack<Object>) () -> {
            // 저장로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("ex 예외발생");
            }
            // 상품 저장에 1초
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millSecond) {
        try {
            Thread.sleep(millSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
