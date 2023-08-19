package br.com.api.produtos.interceptor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer{
	
    @Override
    public void addInterceptors(InterceptorRegistry registry){
    	registry.addInterceptor(AuthInterceptor()).excludePathPatterns("/listar");
    }
    
    @Bean 
    public AuthInterceptor AuthInterceptor() {
        return new AuthInterceptor();
    }
}

