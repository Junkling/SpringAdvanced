package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end() {
        TraceStatus st1 = trace.begin("hello1");
        TraceStatus st2 = trace.begin("hello2");
        trace.end(st2);
        trace.end(st1);

    }

    @Test
    void begin_exception() {
        TraceStatus st1 = trace.begin("hello1");
        TraceStatus st2 = trace.begin("hello2");
        trace.exception(st2,new IllegalArgumentException());
        trace.exception(st1,new IllegalArgumentException());
    }
}