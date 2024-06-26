package com.igorcma.workshopmongo.services;

import com.igorcma.workshopmongo.domain.User;
import com.igorcma.workshopmongo.dto.UserDTO;
import com.igorcma.workshopmongo.repository.UserRepository;
import com.igorcma.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    public void delete(String id) {
        User user = findById(id);
        repo.delete(user);
    }

    public User update(User obj) {
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void updateData(User newObj, User obj) {
        if (obj.getName() != null)
            newObj.setName(obj.getName());
        if (obj.getEmail() != null)
            newObj.setEmail(obj.getEmail());
    }

}