package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ProxyFactoryTest {

    @Test
    void 인터페이스_존재_Jdk_동적프록시() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxy={}", proxy.getClass());

        proxy.save();
        log.info("---");
        proxy.find();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertTrue(AopUtils.isJdkDynamicProxy(proxy));
        assertFalse(AopUtils.isCglibProxy(proxy));
    }

    @Test
    void 구체클래스만_존재_CGLIB_동적프록시() {
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxy={}", proxy.getClass());

        proxy.call();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
    }

    @Test
    void proxyTargetClass_인터페이스_존재해도_CGLIB_사용_클래스_기반_프록시() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxy={}", proxy.getClass());

        proxy.save();
        log.info("---");
        proxy.find();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
    }
}
