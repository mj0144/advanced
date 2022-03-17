package hello.proxy.trace.callback;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

/**
 * 템플릿 역할.
 */
public class TraceTemplate {
    private final LogTrace logTrace;

    public TraceTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public <T> T execute(String message, TraceCallback<T> callback){
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);

            // 로직호출
            T result = callback.call();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;

        }
    }
}
