package br.rkdev.personapi.service;

import br.rkdev.personapi.dto.MessageResponseDTO;
import br.rkdev.personapi.dto.request.PersonDTO;
import br.rkdev.personapi.entity.Person;
import br.rkdev.personapi.exception.PersonNotFoundException;
import br.rkdev.personapi.mapper.PersonMapper;
import br.rkdev.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createdMessageResponse("Created person with ID ", savedPerson.getId());
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = isExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        isExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        isExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createdMessageResponse("Updated person with ID ", updatedPerson.getId());
    }


    private Person isExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createdMessageResponse(String message, Long id) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
