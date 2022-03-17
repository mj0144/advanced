package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null); // 문법상 어쩔수없이 사용.
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");

            //target호출
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }

    @RequiredArgsConstructor
    public static class OrderControllerInterfaceProxy implements OrderControllerV1 {

        private final OrderControllerV1 target;
        private final LogTrace logTrace;

        @Override
        public String request(String itemId) {
            TraceStatus status = null;
            try {
                status = logTrace.begin("OrderController.request()");

                //target호출
                String request = target.request(itemId); // 실제객체 호출
                logTrace.end(status);
                return request;
            } catch (Exception e) {
                logTrace.exception(status, e);
                throw e;
            }
        }

        @Override
        public String noLog() {
            return target.noLog();
        }
    }
}
