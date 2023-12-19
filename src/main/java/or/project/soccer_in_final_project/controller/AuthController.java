package or.project.soccer_in_final_project.controller;//package or.shani.all_nba_final_project_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.SignInRequestDto;
import or.project.soccer_in_final_project.dto.SignInResponseDto;
import or.project.soccer_in_final_project.dto.SignUpRequestDto;
import or.project.soccer_in_final_project.dto.UserResponseDto;
import or.project.soccer_in_final_project.security.JWTProvider;
import or.project.soccer_in_final_project.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserDetailsServiceImpl authService;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid SignUpRequestDto dto) {
        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto dto) {
        var user = authService.loadUserByUsername(dto.getUsername());

        var savedPassword = user.getPassword();
        var givenPassword = dto.getPassword();
        if (passwordEncoder.matches(givenPassword, savedPassword)) {
            //grant:
            var token = jwtProvider.generateToken(user.getUsername());

            return ResponseEntity.ok(new SignInResponseDto(token));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
