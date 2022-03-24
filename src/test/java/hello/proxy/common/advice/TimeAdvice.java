package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    //

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startImte = System.currentTimeMillis();

        //Object result = method.invoke(target, args); //리플렉션을 사용해서 target 인스턴스의 메서드를 실행한다. args는 메서드 호출시 넘겨줄 인수이다.

        Object result = invocation.proceed();// 알아서 타겟을 찾아서 타겟의 실체를 호출해줌.
        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startImte;
        log.info("TimeProxy 종료 resultTime = {}", resultTime);

        return result;
    }
}
