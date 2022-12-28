package hello.advanced.v4;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                // 저장로직
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("ex 예외발생");
                }
                // 상품 저장에 1초
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.request()");
    }

    private void sleep(int millSecond) {
        try {
            Thread.sleep(millSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
