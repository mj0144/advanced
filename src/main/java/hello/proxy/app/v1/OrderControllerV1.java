package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping // 스프링은 @controller 또는 @requestMapping 이 있어야 스프링 컨트롤러로 인식함.
@ResponseBody
public interface OrderControllerV1 {
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId); // 자바 버전에 따라서 itemId를 제대로 인식하지 못할 때가 있어 requestParam으로 명확하게 명시.

    @GetMapping("/v1/no-log")
    String noLog();
}
