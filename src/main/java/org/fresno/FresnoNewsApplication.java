package org.fresno;


import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.fresno.adapter.grpc.ExtractKeywordsClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "org.fresno")
public class FresnoNewsApplication {

    @Bean(name = "channel")
    public Channel channel() {
        String serverAddress = "localhost:50051";
        return ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();
    }

}
