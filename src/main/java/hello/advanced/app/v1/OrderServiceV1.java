package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 traceV1;

    public void orderItem(String itemId) {
        TraceStatus st = null;
        try{
            st = traceV1.begin("OrderServiceV1.orderItem()");
            orderRepositoryV1.save(itemId);
            traceV1.end(st);
        }catch (Exception e){
            traceV1.exception(st, e);
            throw e;
        }
    }

}
