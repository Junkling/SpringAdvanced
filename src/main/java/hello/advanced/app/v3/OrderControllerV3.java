package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderServiceV3;
    private final LogTrace traceV3;

    @GetMapping("/v3/request")
    public String request(String itemId) {
        TraceStatus st = null;
        try{
            st = traceV3.begin("OrderControllerV3.request");
            orderServiceV3.orderItem(itemId);
            traceV3.end(st);
            return "ok";
        }catch (Exception e){
            traceV3.exception(st, e);
            throw e;
        }
    }
}
