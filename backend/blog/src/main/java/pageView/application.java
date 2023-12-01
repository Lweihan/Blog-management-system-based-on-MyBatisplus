package pageView;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ mean : this class belongs to run class.
 */
@SpringBootApplication
@MapperScan("pageView.mapper")
@EnableScheduling
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class, args);
    }
}
