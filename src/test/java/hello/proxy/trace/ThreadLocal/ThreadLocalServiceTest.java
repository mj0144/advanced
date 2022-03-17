package hello.proxy.trace.ThreadLocal;


import hello.proxy.trace.ThreadLocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService fieldService = new ThreadLocalService();

    @Test
    void field(){
        log.info("main test");
        Runnable userA = () ->{
            fieldService.logic("userA");
        };
        Runnable userB = () ->{
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadA.setName("thread-B");

        threadA.start();
        //sleep(2000); // 동시성 문제 발생X
        sleep(100); // 동시성 문제 발생. 조회할 때, 원하는 값이 조회되지 않음.
        threadB.start();

        sleep(2000); // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int miilis) {
        try {
            Thread.sleep(miilis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
