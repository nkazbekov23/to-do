package com.projectbynurs.controller;

import com.projectbynurs.ResourceNotFoundException;
import com.projectbynurs.reprmodel.ToDoRe;
import com.projectbynurs.service.ToDoService;
import com.projectbynurs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TodoController {

    private ToDoService toDoService;

    private UserService userService;

    @Autowired
    public TodoController(ToDoService toDoService, UserService userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        List<ToDoRe> todos = toDoService.findToDoByUserId(userService.getCurrentUserId()
                .orElseThrow(ResourceNotFoundException::new));

        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/todo/{id}")
    public String todoPage(@PathVariable("id") Integer id, Model model) {
        ToDoRe toDoR = toDoService.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("todo", toDoR);
        return "todo";
    }

    @GetMapping("/todo/create")
    public String createToDoPage(Model model) {
        model.addAttribute("todo", new ToDoRe());
        return "todo";
    }

    @PostMapping("/todo/create")
    public String createToDoPost(@ModelAttribute("todo") @Valid ToDoRe toDoRe,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo";
        }

       toDoService.save(toDoRe);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteToDo(@PathVariable Integer id) {
        toDoService.delete(id);
        return "redirect:/";
    }
}
