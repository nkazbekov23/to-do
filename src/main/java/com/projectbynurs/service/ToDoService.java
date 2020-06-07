package com.projectbynurs.service;

import com.projectbynurs.entity.ToDo;
import com.projectbynurs.repository.ToDoRepository;
import com.projectbynurs.repository.UserRepository;
import com.projectbynurs.reprmodel.ToDoRe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.projectbynurs.security.Utils.getCurrentUser;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ToDoService {

    private ToDoRepository toDoRepository;

    private UserRepository userRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public Optional<ToDoRe> findById(Integer id) {
        return toDoRepository.findById(id).map(ToDoRe::new);
    }

    public List<ToDoRe> findToDoByUserId(Integer userId) {
        return toDoRepository.findToDosByUserId(userId);
    }

    public void save(ToDoRe toDoRe) {

        getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .ifPresent(user -> {
                    ToDo toDo = new ToDo();
                    toDo.setId(toDoRe.getId());
                    toDo.setDescription(toDoRe.getDescription());
                    toDo.setTargetDate(toDoRe.getTargetDate());
                    toDo.setUser(user);
                    toDoRepository.save(toDo);
                });

    }

    public void delete(Integer id) {
        toDoRepository.findById(id).ifPresent(toDo -> toDoRepository.delete(toDo));
    }
}
