package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 전략패턴 사용
     */
    @Test
    void strategyV1(){
        ContextV2 contextV1 = new ContextV2();
        contextV1.execute(new StrategyLogic1());
        contextV1.execute(new StrategyLogic2());
    }

    /**
     * 전략패턴 익명내부 클래스
     */
    @Test
    void strategyV2(){
        ContextV2 contextV1 = new ContextV2();
        contextV1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1실행");
            }
        });
        contextV1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2실행");
            }
        });
    }

    /**
     * 전략패턴 익명내부 클래스 -> 람다
     */
    @Test
    void strategyV3(){
        ContextV2 contextV1 = new ContextV2();
        contextV1.execute(() -> log.info("비즈니스 로직 1실행"));
        contextV1.execute(() -> log.info("비즈니스 로직 2실행"));
    }

}
