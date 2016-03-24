package user.list.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.Collections;
import java.util.stream.Collectors;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"user.list"})
@PropertySource("classpath:application.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private MessageSource messageSource;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/sign/in");
    }

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCache(false);
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageSource(messageSource);
        templateEngine.setAdditionalDialects(
                Collections.singletonList(new SpringSecurityDialect())
                        .stream()
                        .collect(Collectors.toSet()));
        return templateEngine;
    }

    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
/*
    @Bean
    public JacksonAnnotationIntrospector jacksonAnnIntrospector() {
        return new JacksonAnnotationIntrospector();
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper result = new ObjectMapper();
        result.setAnnotationIntrospector(jacksonAnnIntrospector());
        result.getSerializationConfig().withInsertedAnnotationIntrospector(jacksonAnnIntrospector());
        result.getDeserializationConfig().withInsertedAnnotationIntrospector(jacksonAnnIntrospector());
        //result.registerModule(new JavaTimeModule());
        return result;
    }*/
/*
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter json = new MappingJackson2HttpMessageConverter();
        json.setObjectMapper(jacksonObjectMapper());
        converters.add(json);
        converters.add(new FormHttpMessageConverter());
    }*/

}
