package nl.rafaelmaia.onboardingfn.onboarding;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class StartOnboardingProcessHandler {


    @FunctionName("processResume")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request, ExecutionContext context) {

        var userId = request.getQueryParameters().get("userId");

        // Start user Onboarding things

        var buddies = List.of( "Anakin Skywalker", "Ash Ketchum", "Pegasus Seiya" );

        String random = buddies.get(new Random().nextInt(buddies.size()));

        return request.createResponseBuilder(HttpStatus.OK)
                .body(random)
                .header("Content-Type", "application/json")
                .build();
    }




}
