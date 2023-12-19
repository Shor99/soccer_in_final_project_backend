package or.project.soccer_in_final_project.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.TransferRequestDto;
import or.project.soccer_in_final_project.dto.TransferResponseDto;
import or.project.soccer_in_final_project.dto.UserRequestDto;
import or.project.soccer_in_final_project.dto.UserResponseDto;
import or.project.soccer_in_final_project.entities.Role;
import or.project.soccer_in_final_project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class userController {
    private final UserService userService;
    @GetMapping("/users/{id}/roles")
    public ResponseEntity<Set<Role>> findRoleByUserId(@PathVariable("id") long userId){
        return ResponseEntity.ok(userService.findRoleByUserId(userId));
    }
    @PutMapping("/users/{username}")
    public ResponseEntity<UserResponseDto> updateUserByUsername(
            @Valid @NotNull @PathVariable String  username,
            @Valid @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(userService.updateUserByUsername(dto, username));
    }
    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
