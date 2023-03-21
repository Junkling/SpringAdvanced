package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderServiceV1;
    private final HelloTraceV1 traceV1;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus st = null;
        try{
            st = traceV1.begin("OrderControllerV1.request");
            orderServiceV1.orderItem(itemId);
            traceV1.end(st);
            return "ok";
        }catch (Exception e){
            traceV1.exception(st, e);
            throw e;
        }
    }
}
