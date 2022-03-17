package hello.proxy.trace.hellotrace;

import hello.proxy.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @Test
    void begin() {
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus status = traceV1.begin("hello");
        traceV1.end(status);
    }

    @Test
    void end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }

    @Test
    void exception() {
    }
}