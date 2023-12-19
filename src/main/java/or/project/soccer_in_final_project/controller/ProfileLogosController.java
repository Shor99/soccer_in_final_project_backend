package or.project.soccer_in_final_project.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.ProfileLogoRequestDto;
import or.project.soccer_in_final_project.dto.ProfileLogoResponseDto;
import or.project.soccer_in_final_project.dto.TransferRequestDto;
import or.project.soccer_in_final_project.dto.TransferResponseDto;
import or.project.soccer_in_final_project.service.ProfileLogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/profile_logos")
public class ProfileLogosController {
    private final ProfileLogoService profileLogoService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfileLogoResponseDto> createProfileLogo(@RequestBody @Valid ProfileLogoRequestDto profileLogoRequestDto, UriComponentsBuilder uriBuilder){
        var saved = profileLogoService.createProfileLogo(profileLogoRequestDto);
        var uri = uriBuilder.path("/api/v1/auth/users/profile_logos/{id}").buildAndExpand(saved.getId()).toUri();


        return ResponseEntity.created(uri).body(saved);
    }
    @GetMapping
    public ResponseEntity<List<ProfileLogoResponseDto>> getAllProfileLogos(){
        return ResponseEntity.ok(profileLogoService.getAllProfileLogos());
    }
}
