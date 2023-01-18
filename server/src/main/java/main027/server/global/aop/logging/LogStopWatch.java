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

    private String uuid;

    @PostConstruct
    private void init() {
        this.start();
        this.uuid = UUID.randomUUID().toString().substring(0, 8);
        log.info("========uuid={}'s REQUEST START========", uuid);
    }

    @PreDestroy
    private void destroy() {
        this.stop();
        log.info("========uuid={}'s REQUEST END========[TOTAL TIME={}ms]", uuid, this.getTotalTimeMillis());
    }

    public String getUuid() {
        return uuid;
    }

}
