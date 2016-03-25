package user.list.repositories;

import user.list.entity.UserDtoItem;
import user.list.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRepositoryImpl implements UserFindRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<UserEntity> find(UserDtoItem params) {
        CriteriaBuilder createBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = createBuilder.createQuery(UserEntity.class);
        Root<UserEntity> userEntity = query.from(UserEntity.class);

        Set<Predicate> predicateList = new HashSet<>();
        TypedQuery<UserEntity> typedQuery = entityManager.createQuery(query);


        if (params.getLogin() != null) {
            Predicate predicate = createBuilder.like(
                    createBuilder.upper(userEntity.<String>get("login")),
                    createBuilder.parameter(String.class, "login"));
            predicateList.add(predicate);

            typedQuery.setParameter("login", "%" + params.getLogin().toUpperCase() + "%");
        }

        if (params.getName() != null) {
            Predicate predicate = createBuilder.like(
                    createBuilder.upper(userEntity.<String>get("name")),
                    createBuilder.parameter(String.class, "name"));
            predicateList.add(predicate);

            typedQuery.setParameter("name", "%" + params.getName().toUpperCase() + "%");
        }

        if (params.getCountry() != null) {
            Predicate predicate = createBuilder.like(
                    createBuilder.upper(userEntity.<String>get("country")),
                    createBuilder.parameter(String.class, "country"));
            predicateList.add(predicate);

            typedQuery.setParameter("country", "%" + params.getCountry().toUpperCase() + "%");
        }

        if (params.getSex() != null) {

        }

        if (params.getBirthday() != null) {

        }


        if (predicateList.size() > 0) {
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
        }

        return typedQuery.getResultList();
    }
}
