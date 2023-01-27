package main027.server.global.aop.logging;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * 여러 사용자의 요청이 동시에 들어왔을 때, LogStopWatch가 싱글톤일 경우 uuid 필드를 공유하는 문제가 생김
 * 따라서 사용자의 요청마다 새로운 new LogStopWatch() 객체를 생성해서 지정해줘야 함.
 * 그래서 @Scope(value = "request, ~) 애너테이션을 통해
 * 해당 LogStopWatch 클래스를 싱글톤 객체가 아닌 HTTP 요청마다 새로운 객체를 생성해서 주는 것으로 설정
 */
@Slf4j
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class DataHolder extends StopWatch {

    private Long memberId;
    private String uri;
    private String method;
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
    }

    public void setUri(String uri) {
        this.uri = uri;
        log.info("START [{}] [{}]", uri, method);
    }

    /**
     * 해당 클래스가 없어지기 전 StopWatch 종료 후 생성되어 있던 시간 로깅 후 destroy
     */
    @PreDestroy
    private void destroy() {
        this.stop();
        log.info("END [{}] [{}] [{}]ms", uri, method, this.getTotalTimeMillis());
    }

}
