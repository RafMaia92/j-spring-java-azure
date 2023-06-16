package nl.rafaelmaia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.Random;

@Path("/registerAuto")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterAuto {

    @POST
    public RestResponse<Integer> registerAuto(UserAutoRegistration userAutoRegistration) {
        var spot = new Random().nextInt(300);
        return RestResponse.ok(spot);
    }

}
