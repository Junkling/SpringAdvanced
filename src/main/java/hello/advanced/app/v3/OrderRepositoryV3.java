package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace traceV3;
    public void save(String itemId) {
        TraceStatus st = null;
        try {
            st = traceV3.begin("OrderRepositoryV3.orderItem()");
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("ex예외");
            }
            sleep(1000);
            traceV3.end(st);
        } catch (Exception e) {
            traceV3.exception(st, e);
            throw e;
        }
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
