package main027.server.global.config;

import main027.server.global.aop.logging.DataHolder;
import main027.server.global.auth.Redis.RedisService;
import main027.server.global.auth.filter.JwtAuthenticationFilter;
import main027.server.global.auth.filter.JwtLogoutFilter;
import main027.server.global.auth.filter.JwtReissueFilter;
import main027.server.global.auth.filter.JwtVerificationFilter;
import main027.server.global.auth.handler.*;
import main027.server.global.auth.jwt.JwtTokenizer;
import main027.server.global.auth.userdetails.MemberDetailsService;
import main027.server.global.auth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final RedisService redisService;
    private final MemberDetailsService memberDetailsService;
    private final DataHolder dataHolder;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils,
                                 RedisService redisService,
                                 MemberDetailsService memberDetailsService, DataHolder dataHolder) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.redisService = redisService;
        this.memberDetailsService = memberDetailsService;
        this.dataHolder = dataHolder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/places", "/reviews", "/likes/**", "/bookmarks/**").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/members/**", "/places/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/members/**", "/places/**", "/reviews/**").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/likes/**", "/bookmarks/**", "/members").hasRole("USER")
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Refresh"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * <p>JWT 생성 + 로그인 인증 + 자격 증명 및 검증 필터</p>
     * <p>addFilterAfter : JwtAuthenticationFilter 다음에 바로 JwtVerificationFilter가 동작하도록 설정</p>
     */
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager,
                                                                                          jwtTokenizer, redisService);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils,
                                                                                    redisService);

            JwtReissueFilter jwtReissueFilter = new JwtReissueFilter(jwtTokenizer, redisService, memberDetailsService);

            JwtLogoutFilter jwtLogoutFilter = new JwtLogoutFilter(jwtTokenizer, redisService);



            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class)
                    .addFilterAfter(jwtReissueFilter, JwtVerificationFilter.class)
                    .addFilterAfter(jwtLogoutFilter, JwtVerificationFilter.class);
        }
    }
}
