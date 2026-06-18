package com.example.crudrapido.config;

import com.example.crudrapido.filter.HorarioFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class FilterConfig {

    @Bean 
    public FilterRegistrationBean<HorarioFilter> horarioFilterRegistration(HorarioFilter filter) {
        
        
        FilterRegistrationBean<HorarioFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(filter);
        

        registrationBean.addUrlPatterns("/api/*");
        

        registrationBean.setOrder(1); 
        
        return registrationBean;
    }
}