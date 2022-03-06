package hello.advanced.trace;

// 로그 상태정보. 로그 종료시 사용
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;   // 로그시작시간
    private String message;     // 시작, 종료 로그

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
