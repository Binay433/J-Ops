package com.xchanging.ops.configuration;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.xchanging.ops.converter.AccountConverter;
import com.xchanging.ops.converter.ComponentConverter;
import com.xchanging.ops.converter.EnvironmentConverter;
import com.xchanging.ops.converter.HealthCheckConverter;
import com.xchanging.ops.converter.RoleToUserProfileConverter;
import com.xchanging.ops.converter.ServiceConverter;
import com.xchanging.ops.converter.ServiceSlaConverter;
import com.xchanging.ops.converter.UserConverter;
import com.xchanging.ops.service.ConfigService;
import com.xchanging.ops.service.PublicHolidayService;
import com.xchanging.ops.service.ServicePointsService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.RefDataUtil;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;


@Configuration
@EnableCaching
@EnableWebMvc
@ComponentScan(basePackages = "com.xchanging")
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Bean(name="multipartResolver")
	public StandardServletMultipartResolver resolver(){
		return new StandardServletMultipartResolver();
	}
	
	

	@Autowired
	ConfigService confService;
	@Autowired
	UserService userService;
	@Autowired
	PublicHolidayService holidayService;
	
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
	@Autowired
	AccountConverter accountConverter;
	
	@Autowired
	ServiceConverter serviceConverter;
	
	@Autowired
	ComponentConverter compConverter;
	
	@Autowired
	ServiceSlaConverter slaConverter;
	
	@Autowired
	HealthCheckConverter healthAdmConvereter;
	
	@Autowired
	EnvironmentConverter envConverter;
	
	@Autowired
	UserConverter userConverter;
	
	@Autowired
	ServicePointsService servicePointservice;

	/**
     * Configure ViewResolvers to deliver preferred views.
     */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
	
	/**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
        registry.addConverter(accountConverter);
        registry.addConverter(serviceConverter);
        registry.addConverter(compConverter);
        registry.addConverter(healthAdmConvereter);
        registry.addConverter(slaConverter);
        registry.addConverter(envConverter);
        registry.addConverter(userConverter);
    }
	

    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
    
    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
		CommonUtils.setUserService(userService);
		CommonUtils.setHolidayService(holidayService);
		CommonUtils.setServicePointservice(servicePointservice);
	    RefDataUtil.readProperties(confService);
	    AppInitializer.registration.setMultipartConfig(getMultipartConfigElement());
    }
    private MultipartConfigElement getMultipartConfigElement(){
    	final String LOCATION = System.getProperty("DOC_LOCATION");
    	final long MAX_FILE_SIZE = Long.parseLong(System.getProperty("MAX_FILE_SIZE"));
    	final long MAX_REQUEST_SIZE = Long.parseLong(System.getProperty("MAX_REQUEST_SIZE"));
    	final int FILE_SIZE_THRESHOLD = Integer.parseInt(System.getProperty("FILE_SIZE_THRESHOLD"));
    	
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}
}

