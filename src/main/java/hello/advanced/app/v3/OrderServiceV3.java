package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepositoryV3;
    private final LogTrace traceV3;

    public void orderItem(String itemId) {
        TraceStatus st = null;
        try{
            st = traceV3.begin("OrderServiceV3.orderItem()");
            orderRepositoryV3.save(itemId);
            traceV3.end(st);
        }catch (Exception e){
            traceV3.exception(st, e);
            throw e;
        }
    }

}
