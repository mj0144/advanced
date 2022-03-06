package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId){

        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()");
            // 저장로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외발생");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            // 예외를 꼭 다시 던져줘야함. 그렇지 않으면 예외가 먹힘. 예외가 터지면 예외가 나가야함. 로그기능때문에 예외를 정상흐름처리해서는 안됨.
            throw e;
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {


        }
    }
}
