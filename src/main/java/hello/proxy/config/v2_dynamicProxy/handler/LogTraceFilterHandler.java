package hello.proxy.config.v2_dynamicProxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] pattern; // 특정 패턴일 때만 로그 남기도록.

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] pattern) {
        this.target = target;
        this.logTrace = logTrace;
        this.pattern = pattern;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //메서드 이름 필터
        String methodName = method.getName();
        // ex) save, request, reque*, *est
        if (!PatternMatchUtils.simpleMatch(pattern, methodName)) {
            return method.invoke(target, args);
        }


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
