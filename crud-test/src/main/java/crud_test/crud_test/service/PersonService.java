package crud_test.crud_test.service;

import crud_test.crud_test.entity.Person;
import crud_test.crud_test.exception.RequiredFieldException;
import crud_test.crud_test.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson (@Valid Person person){
        if (person.getName() == null) {
            throw new RequiredFieldException("Field 'name' is required");
        }
        return personRepository.save(person);
    }

    public List<Person> listPeople (){
        return personRepository.findAll();
    }

        public void deletePerson (Long id) {
            personRepository.deleteById(id);
        }
    }
