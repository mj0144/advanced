package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v2_dynamicProxy.DynamicProxyBasicConfig;
import hello.proxy.config.v2_dynamicProxy.DynamicProxyFilterConfig;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigVi1;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV2Config.class) // config 클래스도 스프링빈으로 등록되어야, 입력한 클래스들도 스프링빈들도 등록이 가능함.
//@Import(InterfaceProxyConfig.class) // config 클래스도 스프링빈으로 등록되어야, 입력한 클래스들도 스프링빈들도 등록이 가능함.
//@Import(ConcreteProxyConfig.class) // config 클래스도 스프링빈으로 등록되어야, 입력한 클래스들도 스프링빈들도 등록이 가능함.
//@Import(DynamicProxyBasicConfig.class) // config 클래스도 스프링빈으로 등록되어야, 입력한 클래스들도 스프링빈들도 등록이 가능함.
//@Import(DynamicProxyFilterConfig.class) // config 클래스도 스프링빈으로 등록되어야, 입력한 클래스들도 스프링빈들도 등록이 가능함.
//@Import(ProxyFactoryConfigVi1.class) // config 클래스도 스프링빈으로 등록되어야, 입력한 클래스들도 스프링빈들도 등록이 가능함.
@Import(ProxyFactoryConfigV2.class) // config 클래스도 스프링빈으로 등록되어야, 입력한 클래스들도 스프링빈들도 등록이 가능함.
@SpringBootApplication(scanBasePackages = "hello.proxy.app")
// 내부에 ComponentScan을 하는데, 현재 내가 있는 패키지 및 하위패키지 모두를 ComponentScan를 함.
// 단, scanBasePackages로 패지지 경로 등록하면 등록한 패키지를 스캔함.
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
