package hello.advanced.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TraceStatus {
    private TraceId traceId;
    private Long StartTimes;
    private String message;
}
