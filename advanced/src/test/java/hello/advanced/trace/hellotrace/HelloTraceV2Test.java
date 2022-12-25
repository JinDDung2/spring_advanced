package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloTraceV2Test {

    @Test
    void 시작_끝() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void 시작_예외() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("helloEx1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "helloEx2");
        trace.exception(status2, new RuntimeException());
        trace.exception(status1, new RuntimeException());
    }

}