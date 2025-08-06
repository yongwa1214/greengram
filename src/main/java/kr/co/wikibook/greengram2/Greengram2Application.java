package kr.co.wikibook.greengram2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class Greengram2Application {

    public static void main(String[] args) {
        SpringApplication.run(Greengram2Application.class, args);
    }

}
