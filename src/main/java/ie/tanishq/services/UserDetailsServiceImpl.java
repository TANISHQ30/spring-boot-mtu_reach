package ie.tanishq.services;

import ie.tanishq.dao.UserDao;
import ie.tanishq.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userService.findByEmail(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // create an instance of springs user class using data from our database
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .disabled(false)
                    .accountExpired(false)
                    .accountLocked(false)
                    .roles(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name " + username + " is not Found");
        }
    }

}
