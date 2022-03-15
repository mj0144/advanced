package hello.proxy.trace.strategy;

import hello.proxy.trace.strategy.code.strategy.ContextV1;
import hello.proxy.trace.strategy.code.strategy.Strategy;
import hello.proxy.trace.strategy.code.strategy.StrategyLogic1;
import hello.proxy.trace.template.code.AbstractTemplate;
import hello.proxy.trace.template.code.SubClassLogic1;
import hello.proxy.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime ={}", resultTime);

    }

    private void logic2(){
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime ={}", resultTime);

    }

    /**
     *x 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();

    }

    /**
     * 익명내부클래스 사용하여 템플릿 패턴 사용
     */
    @Test
    void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate(){

            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        log.info("클래스 이름1 : {}", template1.getClass());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate(){

            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("클래스 이름2 : {}", template2.getClass());
        template2.execute();
    }

    /**
     * 전략패턴 사용
     */
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic1 strategyLogic2 = new StrategyLogic1();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    @Test
    void strategyV2(){
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        log.info("startegyLocig1 = {}", strategyLogic1.getClass());
        contextV1.execute();


        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        log.info("startegyLocig2 = {}", strategyLogic2.getClass());
        contextV2.execute();

    }

    /**
     * 익명내부클래스
     */
    @Test
    void strategyV3(){

        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        }
        );
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        contextV2.execute();

    }

    /**
     * 익명내부 클래스 -> 람다(java8) : 람다로 변경하기 위해서는 인터페이스 안에 메서드가 1개여야 함.
     */
    @Test
    void strategyV4(){

        ContextV1 context1 = new ContextV1(() -> {
            log.info("비즈니스 로직1 실행");
        });

        context1.execute();

        ContextV1 context2 = new ContextV1(() -> {
            log.info("비즈니스 로직2 실행");
        });

        context2.execute();

    }
}