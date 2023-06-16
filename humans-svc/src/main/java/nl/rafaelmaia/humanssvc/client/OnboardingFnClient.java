package nl.rafaelmaia.humanssvc.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface OnboardingFnClient {

    @GetExchange
    String getBuddy(@RequestParam String userId);
}
