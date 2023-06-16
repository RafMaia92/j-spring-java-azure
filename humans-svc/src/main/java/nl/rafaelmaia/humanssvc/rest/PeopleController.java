package nl.rafaelmaia.humanssvc.rest;

import java.util.List;

import nl.rafaelmaia.humanssvc.model.PersonFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nl.rafaelmaia.humanssvc.model.Person;
import nl.rafaelmaia.humanssvc.service.PeopleService;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {

        var response = peopleService.getAll();

        return ResponseEntity.ok(response); 
    }

    @PostMapping
    public ResponseEntity<PersonFile> registerPerson(@RequestBody Person person){
        var response = peopleService.registerPerson(person);
        return ResponseEntity.ok(response);
    }

}
