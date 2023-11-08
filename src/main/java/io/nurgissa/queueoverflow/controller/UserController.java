package io.nurgissa.queueoverflow.controller;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.models.User;
import io.nurgissa.queueoverflow.repository.UserRepository;
import io.nurgissa.queueoverflow.service.UserService;
import io.nurgissa.queueoverflow.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User")
//@Hidden To hint specific endpoints or controllers
public class UserController {
    private final UserServiceImpl userService;

    @Operation(
            description = "Get endpoints for User",
            summary = "this is a summary, be funny and happy from 7/11/23",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorised / Invalid Token",
                            responseCode = "403"
                    )
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserByID(id).orElse(null));
    }

    @GetMapping("/")
//    @Hidden To hint specific endpoints or controllers
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
