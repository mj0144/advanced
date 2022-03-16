package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
       log.info("MessageDEcorator");
        String operation = component.operation();
        String decoResult = "***********" + operation + "*************";
        log.info("MessageDecorator 꾸미기 적용전 = {}, 적용후 = {}", operation, decoResult);
        return decoResult;

    }


}
