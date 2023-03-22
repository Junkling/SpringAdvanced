package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0(){
        logic1();
        logic2();

        Strategy strategy = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategy);
        contextV1.execute();
    }
    @Test
    void strategyV1(){
        Strategy strategy1 = new StrategyLogic1();
        Strategy strategy2 = new StrategyLogic2();
        ContextV1 contextV1 = new ContextV1(strategy1);
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV1.execute();
        contextV2.execute();
    }
    @Test
    void strategyV2(){
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1");
            }
        };
        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategy1);
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV1.execute();
        contextV2.execute();
        log.info("Strategy1={}", strategy1.getClass());
        log.info("Strategy2={}", strategy2.getClass());
    }
    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1");
            }
        });
        ContextV1 contextV2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2");
            }
        });
        contextV1.execute();
        contextV2.execute();
    }

    @Test
    void strategyV4(){
        //람다로 변경 할 시 메서드가 1개만 있어야한다!!
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직 1"));
        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직 2"));
        contextV1.execute();
        contextV2.execute();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직 1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직 2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
