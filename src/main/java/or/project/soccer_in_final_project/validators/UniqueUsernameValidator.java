package or.project.soccer_in_final_project.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.entities.User;
import or.project.soccer_in_final_project.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

        //if user does not exist -> VALID
        return user.isEmpty();
    }
}