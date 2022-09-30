package com.mindhub.skywalkies.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .antMatchers(HttpMethod.GET, "/api/clients").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/clients/current").hasAnyAuthority("ADMIN", "CLIENT")
                .antMatchers(HttpMethod.POST, "/api/products/add").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/products/addProduct").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/bills/payment").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers(HttpMethod.POST, "/products/addCart").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/products/edit").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "client/avatar/current").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/bills/payment").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers("/assets/**", "/assets/js/**", "/assets/img/**,", "/assets/styles/**", "/assets/vid/**").permitAll()
                .antMatchers("/web/manager.html").hasAuthority("ADMIN")
                .antMatchers("/web/cart.html", "/web/character_customization.html", "/web/payment.html").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers("/web/index.html", "/web/verified.html").permitAll();
        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");


        http.logout().logoutUrl("/api/logout");

        http.csrf().disable();

        http.headers().frameOptions();

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
