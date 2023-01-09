package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    // hello.app.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {} // pointcut 시그니처

    // 클래스 이름 패턴 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    // allOrder와 allService 조합
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
