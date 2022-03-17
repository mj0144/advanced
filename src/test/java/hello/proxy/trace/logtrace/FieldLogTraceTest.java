package hello.proxy.trace.logtrace;

import hello.proxy.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        trace.end(status2);
        trace.end(status1);


    }

    @Test
    void beginException() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1,  new IllegalStateException());


    }

    @Test
    void syncTraceId() {
    }

    @Test
    void end() {
    }

    @Test
    void exception() {
    }
}