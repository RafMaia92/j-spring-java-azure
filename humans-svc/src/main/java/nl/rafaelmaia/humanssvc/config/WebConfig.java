package nl.rafaelmaia.humanssvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rafaelmaia.humanssvc.client.OnboardingFnClient;
import nl.rafaelmaia.humanssvc.client.ParkingFnClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {

    @Bean
    ParkingFnClient parkingFnClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl("https://jsh-parking-fn.azurewebsites.net/api/registerAuto/")
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(ParkingFnClient.class);
    }

    @Bean
    OnboardingFnClient onboardingFnClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl("https://jsh-onboarding-fn.azurewebsites.net/api/processResume")
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(OnboardingFnClient.class);
    }

}
