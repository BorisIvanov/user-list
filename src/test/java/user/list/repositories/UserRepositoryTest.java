package user.list.repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import user.list.config.WebConfig;
import user.list.entity.UserDtoItem;
import user.list.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void upSuccess() throws Exception {

        LocalDate localDate = LocalDate.of(2012, 12, 31);

        UserEntity userEntity = userRepository.readByLogin("login--login");
        if (userEntity != null) {
            userRepository.delete(userEntity.getId());
        }

        userEntity = new UserEntity();
        userEntity.setLogin("login--login");
        userEntity.setPassword("qazwsx");
        userEntity.setCountry("Country-Country");
        userEntity.setName("Name-Name");
        userEntity.setBirthday(localDate);
        userEntity.setSex(Byte.valueOf("2"));
        userRepository.save(userEntity);

        UserDtoItem userDtoItem = new UserDtoItem();
        userDtoItem.setLogin("login");
        userDtoItem.setCountry("Country");
        userDtoItem.setName("Name");
        userDtoItem.setSex(Byte.valueOf("2"));
        userDtoItem.setBirthday(localDate);

        List<UserEntity> result = userRepository.find(userDtoItem);
        assertTrue(result.size() == 1);
    }

}
