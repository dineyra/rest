package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;


import java.util.Optional;



@Service
public class UserDetailServ implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailServ(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    @Transactional
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUserName(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не найден!", username));
        } else {
            return new MyUserDetails(user.get());
        }
    }

    public Optional<User> findByUsername (String username) {
        return usersRepository.findByUserName(username);
    }

}

