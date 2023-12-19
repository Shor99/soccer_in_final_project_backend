package or.project.soccer_in_final_project.service;

import or.project.soccer_in_final_project.dto.ProfileLogoRequestDto;
import or.project.soccer_in_final_project.dto.ProfileLogoResponseDto;

import java.util.List;

public interface ProfileLogoService {
    ProfileLogoResponseDto createProfileLogo(ProfileLogoRequestDto profileLogoRequestDto);

    List<ProfileLogoResponseDto> getAllProfileLogos();
}
