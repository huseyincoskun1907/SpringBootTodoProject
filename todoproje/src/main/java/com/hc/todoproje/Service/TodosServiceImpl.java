package com.hc.todoproje.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hc.todoproje.Repository.TodosRepository;
import com.hc.todoproje.Repository.UserRepository;
import com.hc.todoproje.entity.Todos;
import com.hc.todoproje.entity.User;

@Service("todosService")
public class TodosServiceImpl implements TodosService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TodosRepository todosRepository;

    @Override
    public void addTodos(String name) {

        Todos todos = new Todos(name, false);

        todos.setUser(getCurrentUser());
        todosRepository.save(todos);
    }

    @Override
    public Todos findByName(String name) {
        return todosRepository.findByName(name);
    }

    @Override
    public void updateComplated(String name) {

        Todos todos = findByName(name);

        todos.setCompleted(true);

        todosRepository.save(todos);
    }

    @Override
    public void deleteTodos(String name) {

        Todos todos = findByName(name);

        todosRepository.delete(todos);
    }

    @Override
    public void findByCompletedAndUserId(Model model) {

        User currentUser = getCurrentUser();

        model.addAttribute("todosList", todosRepository.findByCompletedAndUserId(false, currentUser.getId()));

        model.addAttribute("todosDoneList", todosRepository.findByCompletedAndUserId(true, currentUser.getId()));

        model.addAttribute("username", currentUser.getUsername());
    }

    public User getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(auth.getName());
    }

}