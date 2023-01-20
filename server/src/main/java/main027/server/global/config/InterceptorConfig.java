package main027.server.global.config;

import lombok.RequiredArgsConstructor;
import main027.server.global.aop.logging.DataHolder;
import main027.server.global.auth.jwt.JwtTokenizer;
import main027.server.global.interceptor.DataInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final JwtTokenizer jwtTokenizer;
    private final DataHolder dataHolder;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DataInterceptor(jwtTokenizer, dataHolder))
                .order(1)
                .addPathPatterns("/**");
    }
}
