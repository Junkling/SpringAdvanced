package hello.advanced.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {
    private String id;
    private Integer level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, Integer level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        //8자리만 사용
        return UUID.randomUUID().toString().substring(0,8);
    }
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }
    public TraceId createPreId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
