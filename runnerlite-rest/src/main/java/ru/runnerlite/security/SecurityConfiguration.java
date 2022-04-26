package ru.runnerlite.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           PasswordEncoder passwordEncoder,
                           UserAuthService userAuthService) throws Exception {

//        для тестирования
        auth.inMemoryAuthentication()
                .withUser("test_admin")
                .password(passwordEncoder.encode("qwerty123")) //шифрование пароля в оперативной памяти
                .roles("ADMIN")
                .and()
                .withUser("test_user")
                .password(passwordEncoder.encode("123qwerty"))
                .roles("USER");
        auth.userDetailsService(userAuthService);
    }


    @Configuration
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private CookieCsrfTokenRepository cookieCsrfTokenRepository() {
            CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();
            csrfTokenRepository.setCookieHttpOnly(false);
            csrfTokenRepository.setCookiePath("/");
            return csrfTokenRepository;
        }

        @Configuration
        @Order(2)
        public static class ApiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                        .authorizeRequests()
                        .anyRequest().authenticated()
                        .and()
                        .httpBasic()// включаем базовую авторизацию ниже выдаем джесон при не удачной авторизации
                        .authenticationEntryPoint((reg,resp,exception) ->{
                            resp.setContentType("aplication/json");
                            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().println("{\"error\":\""+exception.getMessage()+"\"}");
                        })
                        .and()
                        .csrf().disable() //выключаем стандартное поддержание авторизации в сессии
                        .sessionManagement() //добавляем кастомный механизм поддержания сессии
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // каждый вызов будет требовать авторизации
                ;
            }
        }

        @Configuration
        @Order(1)
        public static class UiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                        .antMatcher("/login")
                        .authorizeRequests()
                        .antMatchers("/**/*.css", "/**/*.js").permitAll()
                        //TODO .antMatchers("/product/**").permitAll()
                        //TODO .antMatchers("/user/new").permitAll()
                        //TODO .antMatchers("/user/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                        //TODO .antMatchers("/access_denied").authenticated()
                        .and()
                        .formLogin()
                        //TODO .loginPage("/login")
                        //TODO .loginProcessingUrl("/login_processing")
                        //TODO .defaultSuccessUrl("/product")
                        .and()
                        .exceptionHandling()
                        .accessDeniedPage("/access_denied");
            }
        }
    }
}
