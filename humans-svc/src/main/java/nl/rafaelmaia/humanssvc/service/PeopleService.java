package nl.rafaelmaia.humanssvc.service;

import java.util.List;
import java.util.UUID;

import nl.rafaelmaia.humanssvc.client.OnboardingFnClient;
import nl.rafaelmaia.humanssvc.client.ParkingFnClient;
import nl.rafaelmaia.humanssvc.model.PersonFile;
import nl.rafaelmaia.humanssvc.model.UserAutoRegistration;
import org.springframework.stereotype.Service;

import nl.rafaelmaia.humanssvc.model.Person;

@Service
public class PeopleService {

    private final ParkingFnClient parkingFnClient;

    private final OnboardingFnClient onboardingFnClient;

    public PeopleService(ParkingFnClient parkingFnClient, OnboardingFnClient onboardingFnClient) {
        this.parkingFnClient = parkingFnClient;
        this.onboardingFnClient = onboardingFnClient;
    }

    public List<Person> getAll() {

        Person person1 = new Person("Rafael", "a");
        Person person2 = new Person("Meike", "a");
        Person person3 = new Person("Lucia", "a");

        return List.of(person1, person2, person3);
    }

    public PersonFile registerPerson(Person person){

        var userId = UUID.randomUUID().toString();

        var userAutoRegistration = new UserAutoRegistration(userId, person.licensePlate());

        var parkingSpot =  parkingFnClient.registerAuto(userAutoRegistration);

        var buddy = onboardingFnClient.getBuddy(userId);

       return new PersonFile(person.name(), person.licensePlate(), buddy, parkingSpot);
    }
    
}
