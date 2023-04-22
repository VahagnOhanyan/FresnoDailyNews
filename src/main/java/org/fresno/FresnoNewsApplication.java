package org.fresno;


import org.fresno.adapter.python.FindNewsDuplicate;
import org.fresno.adapter.python.HelloService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "org.fresno")
public class FresnoNewsApplication {

    @Bean(name = "helloServiceFactory")
    public FindNewsDuplicate helloFactory() {
        return new FindNewsDuplicate();
    }

    @Bean(name = "helloServicePython")
    public HelloService helloServicePython() throws Exception {
        return helloFactory().getObject();


    }
}
