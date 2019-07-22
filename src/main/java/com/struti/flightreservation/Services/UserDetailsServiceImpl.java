package com.struti.flightreservation.Services;

import com.struti.flightreservation.DAO.Repos.IUserRepository;
import com.struti.flightreservation.Models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found for e mail " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.getRoles());
    }
}
