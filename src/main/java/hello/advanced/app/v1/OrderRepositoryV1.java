package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 traceV1;
    public void save(String itemId) {
        TraceStatus st = null;
        try {
            st = traceV1.begin("OrderRepositoryV1.orderItem()");
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("ex예외");
            }
            sleep(1000);
            traceV1.end(st);
        } catch (Exception e) {
            traceV1.exception(st, e);
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
