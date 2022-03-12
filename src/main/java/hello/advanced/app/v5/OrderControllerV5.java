package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller + @ResponseBody
@RestController
//@RequiredArgsConstructor
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;
    //private final LogTrace trace;

    // 템플릿 싱글톤 생성.
    // 또는 TraceTemplate을 스프링 빈으로 등록해서 주입받아도됨.
    @Autowired
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId){

//        return template.execute("OrderController.request()", new TraceCallback<>() {
//            @Override
//            public String call() {
//                orderService.orderItem(itemId);
//                return "ok";
//            }
//        });

        return template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });


//        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
//            @Override
//            protected String call() {
//                orderService.orderItem(itemId);
//                return "ok";
//            }
//        };

        //return template.execute("OrderController.request()");


    }


}
