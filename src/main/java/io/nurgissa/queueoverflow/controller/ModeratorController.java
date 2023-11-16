package io.nurgissa.queueoverflow.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/moderator")
public class ModeratorController {

    @GetMapping
    public String get() {
        return "GET:: moderator controller";
    }
    @PostMapping
    @Hidden
    public String post() {
        return "POST:: moderator controller";
    }
    @PutMapping
    @Hidden
    public String put() {
        return "PUT:: moderator controller";
    }
    @DeleteMapping
    @Hidden
    public String delete() {
        return "DELETE:: moderator controller";
    }
}
