package user.list.services;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import user.list.entity.UserDtoItem;
import user.list.entity.UserEntity;

public interface UserService extends UserDetailsService {
    void save(UserDtoItem userDtoItem);
    UserEntity readByLogin(String login);
    Page<UserEntity> list(int pageNumber);
}
