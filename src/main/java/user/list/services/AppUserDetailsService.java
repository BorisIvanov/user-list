package user.list.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.list.entity.UserDtoItem;
import user.list.entity.UserEntity;
import user.list.repositories.UserRepository;

@Service
public class AppUserDetailsService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.readByLogin(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userName);
        }
        return userEntity;
    }

    @Override
    public void save(UserDtoItem userDtoItem) {
        userDtoItem.setPassword(passwordEncoder.encode(userDtoItem.getPassword()));
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDtoItem, userEntity);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity readByLogin(String login) {
        return userRepository.readByLogin(login);
    }
}