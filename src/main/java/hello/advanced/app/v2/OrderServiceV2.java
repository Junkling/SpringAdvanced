package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepositoryV2;
    private final HelloTraceV2 traceV2;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus st = null;
        try{
            st = traceV2.beginSync(traceId,"OrderServiceV2.orderItem()");
            orderRepositoryV2.save(st.getTraceId(),itemId);
            traceV2.end(st);
        }catch (Exception e){
            traceV2.exception(st, e);
            throw e;
        }
    }

}
