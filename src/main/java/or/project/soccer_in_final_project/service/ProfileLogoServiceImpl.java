package or.project.soccer_in_final_project.service;

import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.ProfileLogoRequestDto;
import or.project.soccer_in_final_project.dto.ProfileLogoResponseDto;
import or.project.soccer_in_final_project.dto.TransferResponseDto;
import or.project.soccer_in_final_project.entities.ProfileLogo;
import or.project.soccer_in_final_project.entities.Transfer;
import or.project.soccer_in_final_project.repository.ProfileLogosRepository;
import or.project.soccer_in_final_project.repository.TransferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileLogoServiceImpl implements ProfileLogoService {
    private final ModelMapper modelMapper;
    private final ProfileLogosRepository profileLogosRepository;
    @Override
    public ProfileLogoResponseDto createProfileLogo(ProfileLogoRequestDto profileLogoRequestDto) {
        //convert requestDTO To Entity
        var entity = modelMapper.map(profileLogoRequestDto, ProfileLogo.class);
        //save the entity
        var saved = profileLogosRepository.save(entity);
        //convert saved entity to ResponseDto
        return modelMapper.map(saved, ProfileLogoResponseDto.class);
    }

    @Override
    public List<ProfileLogoResponseDto> getAllProfileLogos() {
        return profileLogosRepository
                .findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProfileLogoResponseDto.class))
                .toList();
    }
}
