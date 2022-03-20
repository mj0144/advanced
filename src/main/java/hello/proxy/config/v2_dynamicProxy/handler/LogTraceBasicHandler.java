package hello.proxy.config.v2_dynamicProxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try {

            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";

            //status = logTrace.begin("OrderController.request()"); // 직접개발할때.
            status = logTrace.begin(message); // 동적으로 사용

            //target호출
            Object result = method.invoke(target, args);// 실제객체 호출
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
