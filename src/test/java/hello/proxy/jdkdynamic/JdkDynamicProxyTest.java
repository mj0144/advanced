package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA(){
        Ainterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //Object proxy = Proxy.newProxyInstance(Ainterface.class.getClassLoader(), new Class[]{Ainterface.class}, handler);
        Ainterface proxy = (Ainterface) Proxy.newProxyInstance(Ainterface.class.getClassLoader(), new Class[]{Ainterface.class}, handler);

        // handler의 invoke가 실행됨.
        // handler 내부 로직중 method.invoke에서 실제 해당 인터페스트의 call을 호출함.
        proxy.call();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

    }

    @Test
    void dynamicB(){
        Binterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //Object proxy = Proxy.newProxyInstance(Binterface.class.getClassLoader(), new Class[]{Binterface.class}, handler);
        Binterface proxy = (Binterface) Proxy.newProxyInstance(Ainterface.class.getClassLoader(), new Class[]{Binterface.class}, handler);

        // handler의 invoke가 실행됨.
        // handler 내부 로직중 method.invoke에서 실제 해당 인터페스트의 call을 호출함.
        proxy.call();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

    }
}
