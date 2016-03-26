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
import java.time.LocalDate;
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

        if (params.getLogin() != null) {
            Predicate predicate = createBuilder.like(
                    createBuilder.upper(userEntity.get("login")),
                    createBuilder.parameter(String.class, "login"));
            predicateList.add(predicate);
        }

        if (params.getName() != null) {
            Predicate predicate = createBuilder.like(
                    createBuilder.upper(userEntity.get("name")),
                    createBuilder.parameter(String.class, "name"));
            predicateList.add(predicate);
        }

        if (params.getCountry() != null) {
            Predicate predicate = createBuilder.like(
                    createBuilder.upper(userEntity.get("country")),
                    createBuilder.parameter(String.class, "country"));
            predicateList.add(predicate);
        }

        if (params.getSex() != null) {
            Predicate predicate = createBuilder.equal(
                    userEntity.get("sex"),
                    createBuilder.parameter(Byte.class, "sex"));
            predicateList.add(predicate);
        }

        if (params.getBirthday() != null) {
            Predicate predicate = createBuilder.equal(
                    userEntity.get("birthday"),
                    createBuilder.parameter(LocalDate.class, "birthday"));
            predicateList.add(predicate);
        }


        if (predicateList.size() > 0) {
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
        }

        TypedQuery<UserEntity> typedQuery = entityManager.createQuery(query);
        if (params.getLogin() != null) {
            typedQuery.setParameter("login", "%" + params.getLogin().toUpperCase() + "%");
        }
        if (params.getName() != null) {
            typedQuery.setParameter("name", "%" + params.getName().toUpperCase() + "%");
        }
        if (params.getCountry() != null) {
            typedQuery.setParameter("country", "%" + params.getCountry().toUpperCase() + "%");
        }
        if (params.getSex() != null) {
            typedQuery.setParameter("sex", params.getSex());
        }
        if (params.getBirthday() != null) {
            typedQuery.setParameter("birthday", params.getBirthday());
        }
        return typedQuery.getResultList();
    }
}
