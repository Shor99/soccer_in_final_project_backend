package or.project.soccer_in_final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import or.project.soccer_in_final_project.dto.CommentResponseDto;
import or.project.soccer_in_final_project.dto.TransferResponseDto;
import or.project.soccer_in_final_project.dto.UserRequestDto;
import or.project.soccer_in_final_project.dto.UserResponseDto;
import or.project.soccer_in_final_project.entities.Role;
import or.project.soccer_in_final_project.entities.Transfer;
import or.project.soccer_in_final_project.entities.User;
import or.project.soccer_in_final_project.error.ResourceNotFoundException;
import or.project.soccer_in_final_project.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserByUsername(String username) {
        //assert that the user exists:
        if (!userRepository.existsUserByUsernameIgnoreCase(username)) {
            throw new ResourceNotFoundException("user", username);
        }

        return modelMapper
                .map(
                        userRepository
                                .findByUsernameIgnoreCase(username),
                        UserResponseDto.class
                );
    }
    private User getUserEntity(String username){
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(
                () -> new ResourceNotFoundException("Username", username)
        );
    }
    @Override
    public UserResponseDto updateUserByUsername(UserRequestDto dto, String username) {
        User userFromDb = getUserEntity(username);

        userFromDb.setUsername(dto.getUsername());
        userFromDb.setEmail(dto.getEmail());
        userFromDb.setProfileLogoUrl(dto.getProfileLogoUrl());

        var saved = userRepository.save(userFromDb);

        return modelMapper.map(saved, UserResponseDto.class);
    }

    @Override
    public Set<Role> findRoleByUserId(long userId) {
        //assert that the user exists:
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("user", userId);
        }

        return userRepository
                .findById(userId)
                .get().getRoles();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserResponseDto.class))
                .toList();
    }

}
