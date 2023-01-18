package main027.server.global.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Slf4j
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogStopWatch extends StopWatch {

    /**
     * 해당 HTTP의 요청을 인식할 id (request가 종료될 때 까지 유지)
     */
    private String uuid;

    /**
     * 해당 클래스가 생성되어질 때 StopWatch 시작 후 로깅
     */
    @PostConstruct
    private void init() {
        this.start();
        this.uuid = UUID.randomUUID().toString().substring(0, 8);
        log.info("========uuid={}'s REQUEST START========", uuid);
    }

    /**
     * 해당 클래스가 없어지기 전 StopWatch 종료 후 생성되어 있던 시간 로깅 후 destroy
     */
    @PreDestroy
    private void destroy() {
        this.stop();
        log.info("========uuid={}'s REQUEST END========[TOTAL TIME={}ms]", uuid, this.getTotalTimeMillis());
    }

    public String getUuid() {
        return uuid;
    }

}
