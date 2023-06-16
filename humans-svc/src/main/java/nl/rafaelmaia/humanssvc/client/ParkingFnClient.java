package nl.rafaelmaia.humanssvc.client;

import nl.rafaelmaia.humanssvc.model.UserAutoRegistration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ParkingFnClient {

    @PostExchange
    Integer registerAuto(@RequestBody UserAutoRegistration userAutoRegistration);
}
