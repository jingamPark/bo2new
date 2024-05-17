package com.example.bo2.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Log4j2
@EnableMethodSecurity(prePostEnabled = true)
@Configuration //bean등록을 위한 어노테이션
@RequiredArgsConstructor
@EnableWebSecurity //시큐리티 활성화 //요청하는 모든 url이 스프링 시큐리티 제어를 받도록 만듬, 시큐리티 활성화
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("------------------configure--------------------");

        http
                .authorizeHttpRequests( (authorizeHttpRequests) -> authorizeHttpRequests

                //.requestMatchers("/**").permitAll()
                    .requestMatchers("/board/register").authenticated()
                    .requestMatchers("/board/modify").authenticated()
                    .requestMatchers("/user/login").permitAll()
                    .requestMatchers("/**").permitAll()


        )
                .formLogin(
                        formLogin -> formLogin.loginPage("/user/login") //로그인페이지post
                                .defaultSuccessUrl("/board/list") //성공시 이동페이지

                )

                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                                .logoutSuccessUrl("/board/list")
                                .invalidateHttpSession(true)
                )

                .csrf(
                       // csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**"))
                        csrf -> csrf.disable()

                        )

        ;

        return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {



        return new BCryptPasswordEncoder();
    }
    //스프링 시큐리티의 인증처리 , 인증은 로그인
    //AuthenticationManager는 사용자 인증이 앞에서 작성한 UserSecuritySecive와
    //passwordEncoder를 내부적으로 사용, 인증과 권한 부여

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

}
