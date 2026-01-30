package restapi.prac.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restapi.prac.model.User;
import restapi.prac.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public void signup(String username, String password) {

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 암호화

        userRepository.save(user);
    }

    // 로그인 때 사용할 사용자 조회 메서드 추가
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
