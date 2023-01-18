package main027.server.global.aop.logging.aspect;

import lombok.extern.slf4j.Slf4j;
import main027.server.global.aop.logging.LogStopWatch;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeTraceAspect {

    private final LogStopWatch stopWatch;

    public TimeTraceAspect(LogStopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    @Around("@annotation(timeTrace)")
    public Object doTimeTrace(ProceedingJoinPoint joinPoint, TimeTrace timeTrace) throws Throwable {

        String name = joinPoint.getSignature().toShortString();
        String uuid = stopWatch.getUuid();
        int millis = timeTrace.millis();
        final int limitMillis = 300;

        stopWatch.stop();

        doLogTime("-->", uuid, name, stopWatch.getLastTaskTimeMillis(), millis, limitMillis);

        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        doLogTime("<--", uuid, name, stopWatch.getLastTaskTimeMillis(), millis, limitMillis);

        stopWatch.start();

        return result;
    }

    private void doLogTime(String arrow, String uuid, String name, long lastStopWatchTime,
                           int millis, int limitMillis) {
        if (lastStopWatchTime <= millis) {
            log.info(arrow +"[{}][{}ms][{}]", uuid, lastStopWatchTime, name);
        } else if (lastStopWatchTime <= limitMillis) {
            log.warn(arrow +"[{}][{}ms][{}]", uuid, lastStopWatchTime, name);
        } else log.error(arrow +"[{}][{}ms][{}]", uuid, lastStopWatchTime, name);
    }
}
