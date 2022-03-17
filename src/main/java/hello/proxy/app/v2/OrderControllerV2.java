package hello.proxy.app.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping // controller쓰면 componentScan의 대상이 되기 때문에 사용 안함. requestMapping은 대상이 아님.
// 여기서는 ComponentScan을 통한 자동빈등록이 아닌 수동빈등록을 하는것이 목표이기 때문.
@ResponseBody
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;

    public OrderControllerV2(OrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "OK";
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "OK";
    }
}
