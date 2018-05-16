package cn.gyyx.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"cn.gyyx.tasks","cn.gyyx.server"})
@EntityScan("cn.gyyx.bean")
@EnableJpaRepositories("cn.gyyx.dao")
public class TasksreleaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksreleaseApplication.class, args);
	}
}
