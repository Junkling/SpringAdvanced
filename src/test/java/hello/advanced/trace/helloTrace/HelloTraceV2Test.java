package hello.advanced.trace.helloTrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HelloTraceV2Test {
    @DisplayName("begin,end")
    @Test
    void Begin_end() {
        HelloTraceV2 helloTraceV2 = new HelloTraceV2();
        TraceStatus st1 = helloTraceV2.begin("hello1");
        TraceStatus st2 = helloTraceV2.beginSync(st1.getTraceId(),"hello2");

        helloTraceV2.end(st2);
        helloTraceV2.end(st1);
    }
    @DisplayName("begin,exception")
    @Test
    void begin_ex() {
        HelloTraceV2 helloTraceV2 = new HelloTraceV2();
        TraceStatus st1 = helloTraceV2.begin("hello1");
        TraceStatus st2 = helloTraceV2.beginSync(st1.getTraceId(), "hello2");
        helloTraceV2.exception(st2, new IllegalArgumentException());
        helloTraceV2.exception(st1, new IllegalArgumentException());
    }

}