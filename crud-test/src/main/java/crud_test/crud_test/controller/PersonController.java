package crud_test.crud_test.controller;

import crud_test.crud_test.entity.Person;
import crud_test.crud_test.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person){
        personService.createPerson(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Person> listPeople(){
        return personService.listPeople();

    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person){
        return personService.createPerson(person);

    }

    @DeleteMapping ("{id}")
    public void deletePerson (@PathVariable Long id){
        personService.deletePerson(id);
    }
}

