package user.list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import user.list.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserFindRepository {
    UserEntity readByLogin(String login);
}
