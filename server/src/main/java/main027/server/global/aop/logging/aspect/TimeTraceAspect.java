package main027.server.global.aop.logging.aspect;

import lombok.extern.slf4j.Slf4j;
import main027.server.global.aop.logging.DataHolder;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeTraceAspect {

    private final DataHolder dataHolder;

    public TimeTraceAspect(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    /**
     * 커스텀한 {@link DataHolder}를 이용해 @{@link TimeTrace}가 붙은 메서드마다의 걸리는 시간을 측정하는 Advice.
     *
     * <p>
     *
     * @param joinPoint
     * @param timeTrace TimeTrace의 정보 (millis 값을 받아오기 위해 파라미터로 추가)
     * @return 요청한 클래스에 전달할 원본 객체의 응답 값
     * @throws Throwable
     * @millis log.WARN 을 표시할 기준 ms
     * @limitMillis log.ERROR를 표시할 기준 ms
     */
    @Around("@annotation(timeTrace)")
    public Object doTimeTrace(ProceedingJoinPoint joinPoint, TimeTrace timeTrace) throws Throwable {

        String name = joinPoint.getSignature().toShortString().split("\\(")[0] + "()";
        String uri = dataHolder.getUri();
        int millis = timeTrace.millis();
        final int limitMillis = 300;

        dataHolder.stop();

        doLogTime("-->", uri, name, dataHolder.getLastTaskTimeMillis(), millis, limitMillis);

        dataHolder.start();

        Object result = joinPoint.proceed();

        dataHolder.stop();

        doLogTime("<--", uri, name, dataHolder.getLastTaskTimeMillis(), millis, limitMillis);

        dataHolder.start();

        return result;
    }

    /**
     * Loggin을 실행하는 메서드 (중복으로 인한 메서드 추출)
     *
     * @param arrow             로그에 표시될 ->, <- 선택
     * @param uuid              {@link DataHolder} 에 설정된 uuid
     * @param name              Target 메서드의 클래스명과 메서드명
     * @param lastStopWatchTime 마지막으로 측정된 StopWatch의 ms
     */
    private void doLogTime(String arrow, String uuid, String name, long lastStopWatchTime,
                           int millis, int limitMillis) {
        if (lastStopWatchTime <= millis) {
            log.info(arrow + "[{}][{}ms][{}]", uuid, lastStopWatchTime, name);
        } else if (lastStopWatchTime <= limitMillis) {
            log.warn(arrow + "[{}][{}ms][{}]", uuid, lastStopWatchTime, name);
        } else log.error(arrow + "[{}][{}ms][{}]", uuid, lastStopWatchTime, name);
    }
}
