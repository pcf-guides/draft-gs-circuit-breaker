package hello;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.pivotal.config.java.CloudConnectorsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.netflix.discovery.EurekaClientConfig;

@Configuration
@Profile("cloud")
public class CloudConfig extends CloudConnectorsConfig {

    @Bean
    public ConnectionFactory hystrixConnectionFactory() {
        return connectionFactory().hystrixConnectionFactory();
    }

    @Bean
    public EurekaClientConfig eurekaClientConfig() {
        return connectionFactory().eurekaClientConfig();
    }

}