package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements Ainterface{

    @Override
    public String call() {
        log.info("A호출");
        return "a";
    }
}
