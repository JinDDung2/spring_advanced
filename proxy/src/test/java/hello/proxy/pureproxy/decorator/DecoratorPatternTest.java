package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratePatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void 데코레이터_없음() {
        RealComponent realComponent = new RealComponent();
        DecoratePatternClient client = new DecoratePatternClient(realComponent);
        client.execute();
    }

    @Test
    void 데코레이터_패턴_적용V1() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratePatternClient client = new DecoratePatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void 데코레이터_패턴_적용V2() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratePatternClient client = new DecoratePatternClient(timeDecorator);
        client.execute();
    }

}
