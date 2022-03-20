package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * JDK 동적 프록시는 인터페이스를 구현(implement)해서 프록시를 만들지만,
 * CGLIB는 구체 클래스를 상속(extends)해서 프록시를 만듦.
 */
@Slf4j
public class CglibTest {

    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // CGLIB는 Enhancer 를 사용해서 프록시를 생성
        enhancer.setSuperclass(ConcreteService.class); // 동적프록시를 만듦. 구체인 ConcreteService를 상속받은 프록시를 만들어야 함.
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 프록시에 적용할 실행 로직을 할당.
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass = {}", target.getClass()); // ConcreteService
        log.info("proxyClass = {}", proxy.getClass()); // ConcreteService$$EnhancerByCGLIB$$25d6b0e3. ConcreteService을 상속받아 만든 cglib

        proxy.call();


    }
}
