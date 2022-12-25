package hello.advanced.v0;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV0 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("ex 예외발생");
        }
        // 상품 저장에 1초
        sleep(1000);
    }

    private void sleep(int millSecond) {
        try {
            Thread.sleep(millSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
