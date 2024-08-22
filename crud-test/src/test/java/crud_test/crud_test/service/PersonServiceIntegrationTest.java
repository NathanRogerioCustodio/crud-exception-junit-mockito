package crud_test.crud_test.service;

import crud_test.crud_test.entity.Person;
import crud_test.crud_test.exception.RequiredFieldException;
import crud_test.crud_test.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private Person person; //muito importante instanciar antes de tudo

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setUp(){
          person = new Person("Nathan", "28");
    }

    @Test
    public void testCreatePersonWithValidPersonShouldReturnsSavedPerson(){
        // Arrange
        Person person = new Person();
        person.setName("Nathan");

        // Simula o comportamento do repositório
        when(personRepository.save(any(Person.class))).thenReturn(person);

        // Act
        Person savedPerson = personService.createPerson(person);

        // Assert
        assertNotNull(savedPerson);
        assertEquals("Nathan", savedPerson.getName());  // Verifica que o nome é igual ao esperado
        verify(personRepository, times(1)).save(person);  // Verifica que o save foi chamado uma vez
    }

    @Test
    public void testCreatePersonWithNullNameShouldThrowException(){
        // Arrange
        Person person = new Person();
        person.setName(null);

        // Act & Assert
        Exception exception = assertThrows(RequiredFieldException.class, () -> {
            personService.createPerson(person);
        });

        assertEquals("Field 'name' is required", exception.getMessage());
        verify(personRepository, times(0)).save(person);  // Verifica que o save não foi chamado
    }

    @Test
    public void testListPeopleShouldReturnListOfPeople(){
        // Arrange
        Person person1 = new Person();
        person1.setName("Nathan1");
        Person person2 = new Person();
        person2.setName("Nathan2");

        List<Person> people = Arrays.asList(person1, person2);

        // Simula o comportamento do repositório
        when(personRepository.findAll()).thenReturn(people);

        // Act
        List<Person> personList = personService.listPeople();

        // Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());
        assertEquals("Nathan1", personList.get(0).getName());
        assertEquals("Nathan2", personList.get(1).getName());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void testDeletePersonWithValidIdShouldCallDeleteById() {
        // Arrange
        Long personId = 1L;

        // Act
        personService.deletePerson(personId);

        // Assert
        verify(personRepository, times(1)).deleteById(personId);
    }
}
