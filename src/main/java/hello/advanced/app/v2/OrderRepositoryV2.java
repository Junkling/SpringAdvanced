package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 traceV2;
    public void save(TraceId traceId, String itemId) {
        TraceStatus st = null;
        try {
            st = traceV2.beginSync(traceId,"OrderRepositoryV2.orderItem()");
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("ex예외");
            }
            sleep(1000);
            traceV2.end(st);
        } catch (Exception e) {
            traceV2.exception(st, e);
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
