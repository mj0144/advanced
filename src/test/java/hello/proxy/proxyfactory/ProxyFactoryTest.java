package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass()); // jdk 동적프록시

        proxy.save();


        // 프록시 팩토리를 사용할 때는 사용가능.
        // 프록시 팩토리 통하지 않고 jdk동적 프록시 만들어서 사용하면 동작안함.
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue(); //
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isFalse();//오류
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue(); //
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); // 오류
    }


    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB를 사용.")
    void concrteProxy() {
        ConcreteService target = new ConcreteService();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass()); // CGLIB 동적프록시. ConcreteService$$EnhancerBySpringCGLIB$$42770b4

        proxy.call();


        // 프록시 팩토리를 사용할 때는 사용가능.
        // 프록시 팩토리 통하지 않고 jdk동적 프록시 만들어서 사용하면 동작안함.
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }


    @Test
    @DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    void proxyTargetClass() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.setProxyTargetClass(true); // 옵션. 항상 CGLIB로 만들어짐.

        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass()); // jdk 동적프록시

        proxy.save();


        // 프록시 팩토리를 사용할 때는 사용가능.
        // 프록시 팩토리 통하지 않고 jdk동적 프록시 만들어서 사용하면 동작안함.
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue(); //
        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isFalse();//오류
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue(); //
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); // 오류
    }
}
