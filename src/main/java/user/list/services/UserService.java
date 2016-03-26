package user.list.services;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import user.list.entity.UserDtoItem;
import user.list.entity.UserEntity;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(UserDtoItem userDtoItem);
    UserEntity readByLogin(String login);
    Page<UserEntity> list(int pageNumber);
    List<UserEntity> find(UserDtoItem params);
}
