package io.picos.helloworld;

import io.picos.helloworld.jwt.JwtAuthenticationFilter;
import io.picos.helloworld.support.HelloWorldProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(HelloWorldProperties.class)
public class HelloWorldApplication {

    @Configuration
    @ConditionalOnProperty(prefix = "helloworld.jwt", name = "enabled", value = "true", matchIfMissing = false)
    public static class JwtFilterConfigurer {

        @Bean
        public FilterRegistrationBean jwtAuthenticationFilter() {
            final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter(new JwtAuthenticationFilter());
            registrationBean.addUrlPatterns("/*");
            registrationBean.setOrder(0);
            return registrationBean;
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

}
