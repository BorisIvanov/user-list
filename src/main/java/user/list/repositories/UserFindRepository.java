package user.list.repositories;

import user.list.entity.UserDtoItem;
import user.list.entity.UserEntity;

import java.util.List;

public interface UserFindRepository {
    List<UserEntity> find(UserDtoItem userDtoItem);
}
