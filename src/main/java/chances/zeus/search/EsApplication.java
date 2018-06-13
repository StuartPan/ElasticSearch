package chances.zeus.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import chances.zeus.search.filter.WebContextFilter;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class EsApplication {

	@Bean
    public FilterRegistrationBean<WebContextFilter> indexFilterRegistration() {
        FilterRegistrationBean<WebContextFilter> registration = new FilterRegistrationBean<WebContextFilter>(
                new WebContextFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
	
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(EsApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
}
