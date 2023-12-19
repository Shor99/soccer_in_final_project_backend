package or.project.soccer_in_final_project.service;

import or.project.soccer_in_final_project.dto.TransferResponseDto;
import or.project.soccer_in_final_project.dto.UserRequestDto;
import or.project.soccer_in_final_project.dto.UserResponseDto;
import or.project.soccer_in_final_project.entities.Role;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserResponseDto getUserByUsername(String username);

    UserResponseDto updateUserByUsername(UserRequestDto dto, String username);
    Set<Role> findRoleByUserId(long userId);
    List<UserResponseDto> getAllUsers();
}
