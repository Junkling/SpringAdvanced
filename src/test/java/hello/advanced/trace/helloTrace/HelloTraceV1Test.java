package hello.advanced.trace.helloTrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloTraceV1Test {
    @DisplayName("begin,end")
    @Test
    void Begin_end() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus st = helloTraceV1.begin("hello");
        helloTraceV1.end(st);
    }
    @DisplayName("begin,exception")
    @Test
    void begin_ex() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus st = helloTraceV1.begin("hello");
        helloTraceV1.exception(st, new IllegalArgumentException());
    }


}