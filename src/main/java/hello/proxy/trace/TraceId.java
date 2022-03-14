package hello.proxy.trace;

import java.util.UUID;

public class TraceId {

    private String id; // 트랜잭션 아이디
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId(){
        // 생성되는 UUID는 너무 기니깐 8자리로 잘라 사용하자.
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * traceID는 같아도 level은 하나씩 증가해야함.
     * @return
     */
    public TraceId createNextId(){
        return new TraceId(id, level+1);
    }

    public TraceId createPreviousId(){
        return new TraceId(id, level-1);
    }


    public boolean isFirstLevel(){
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
