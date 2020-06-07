package com.projectbynurs.service;

import com.projectbynurs.entity.User;
import com.projectbynurs.repository.UserRepository;
import com.projectbynurs.reprmodel.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.projectbynurs.security.Utils.getCurrentUser;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRep userRep) {
        User user = new User();
        user.setUsername(userRep.getUsername());
        user.setPassword(passwordEncoder.encode(userRep.getPassword()));
        userRepository.save(user);

    }

    public Optional<Integer> getCurrentUserId() {
        Optional<String> currentUser = getCurrentUser();
        if (currentUser.isPresent()) {
            return userRepository.getUserByUsername(currentUser.get())
                    .map(User::getId);
        }

        return Optional.empty();
    }

}
