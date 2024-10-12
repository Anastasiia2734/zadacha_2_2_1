package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;


    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        try {
            String hql = "from User user where user.car.model = :model and user.car.series = :series";
            TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery(hql);
            typedQuery.setParameter("model", model).setParameter("series", series);
            return typedQuery.setMaxResults(1).getSingleResult();
        }
        catch (Exception e) {
            throw new RuntimeException("Ошибка при получении данных пользователя", e);
        }
    }
}

