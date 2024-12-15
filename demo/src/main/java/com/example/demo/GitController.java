package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GitController {

    private final UserService userService;

    @GetMapping("/users/{name}")
    public GitUserDTO getData(@PathVariable String name) {
        System.out.println(userService.getUser(name).getLogin() + userService.getUser(name).getId());
        return userService.getUser(name);
    }


}
