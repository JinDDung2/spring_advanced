package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeDecorator;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void 프록시_없음() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

    @Test
    void 프록시_적용() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeDecorator timeDecorator = new TimeDecorator(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeDecorator);
        client.execute();
    }

}
