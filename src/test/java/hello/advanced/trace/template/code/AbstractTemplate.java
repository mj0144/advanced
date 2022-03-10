package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute(){
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        //log.info("비즈니스 로직 1 실행"); // 이부분만 변하게 하면 됨.
        call(); // 상속. 변하는 부분을 자식클래스에 만들어서 해결.
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime ={}", resultTime);
    }

    protected abstract void call() ;

}
