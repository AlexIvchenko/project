package com.github.habiteria.security.service;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.builders.Users;
import com.github.habiteria.core.entities.imps.KarmaImpl;
import com.github.habiteria.core.entities.imps.UserImpl;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.KarmaRepository;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.dto.UserDto;
import com.github.habiteria.exceptions.client.fields.UsernameAlreadyUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
@Service(value = "userAuthService")
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final KarmaRepository karmaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, HabitRepository habitRepository, KarmaRepository karmaRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.karmaRepository = karmaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isAuthorized(Authentication auth, Long userId) {
        User object = object(userId);
        User subject = subject(auth);
        boolean ret = false;
        if (object != null && subject != null) {
            ret = object.equals(subject);
        }
        return ret;
    }

    private User object(Long userId) {
        return userRepository.findOne(userId);
    }

    private User subject(Authentication auth) {
        return  userRepository.findByUsername(auth.getName());
    }

    @Override
    public boolean isAuthorized(Long userId, Long habitId) {
        User user = object(userId);
        Habit habit = habitRepository.findOne(habitId);
        return habit.getOwner().equals(user);
    }

    @Override
    public User signUp(UserDto dto) {
        checkIdentity(dto);
        UserImpl user = Users.withUsername(dto.getUsername())
                .withPassword(passwordEncoder.encode(dto.getPassword()))
                .withEmail(dto.getEmail())
                .withName(dto.getFirstName(), dto.getLastName());
        KarmaImpl karma = new KarmaImpl();
        userRepository.save(user);
        karma.setOwner(user);
        karma.setValue(100);
        karma.setActualTime(LocalDateTime.now());
        karmaRepository.save(karma);
        return user;
    }

    @Override
    public User currentUser() {
        return subject(SecurityContextHolder.getContext().getAuthentication());
    }

    private void checkIdentity(UserDto user) {
        User loaded = userRepository.findByUsername(user.getUsername());
        if (loaded != null) {
            throw new UsernameAlreadyUsedException(user.getUsername());
        }
    }
}
