package com.damian.newEgg.oauth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().antMatchers(HttpMethod.GET,"/noticia/**","/noticia/paginar/**","/noticia/upload/img/**","/img/**").permitAll()
              /* .antMatchers(HttpMethod.GET,"/api/cliente/{id}").hasAnyRole("USER","ADMIN")
               .antMatchers(HttpMethod.POST,"/api/cliente/upload").hasAnyRole("USER","ADMIN")
               .antMatchers(HttpMethod.POST,"/api/cliente").hasRole("ADMIN")
               .antMatchers("/api/cliente/**").hasRole("ADMIN")*/
             .antMatchers("/usuarios/**").permitAll()
            //   .antMatchers("/api/factura/**").permitAll()
             //  .antMatchers("/api/producto/**").permitAll()
               .anyRequest().authenticated()
               .and().cors().configurationSource(corsConfigurationSource());

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
       // config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
      //  config.setAllowedOriginPatterns(Arrays.asList("*"));
      //  config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:80", "http://localhost:8089"));
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:4200", "*" ));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE","PUT","OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-type", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }
@Bean
    public FilterRegistrationBean<CorsFilter>corsFilter(){
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
    }

}
