package main027.server.global.aop.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 해당 애노테이션을 메서드에 적용할 경우, 해당 메서드 <-> 다음 애노테이션이 적용된 메서드까지의 소요시간을 측정합니다.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeTrace {

    /**
     *  Aspect에서 log.WARN을 표시할 시간 기준 ms
     */
    int millis() default 50;

}
