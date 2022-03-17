package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest(){
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest(){
        RealSubject realSubject = new RealSubject(); // 실제객체
        CacheProxy cacheProxy = new CacheProxy(realSubject); // 프록시
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy); // 클라이언트
        client.execute(); // 첫 실행때는 실제객체를 호출
        client.execute(); // 캐시값
        client.execute(); // 캐시값
    }
}
