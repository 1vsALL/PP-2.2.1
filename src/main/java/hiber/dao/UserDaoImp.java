package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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

    @Override
    public void saveCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User getUser(Car car) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u where u.car.model=:m and u.car.series=:s", User.class)
                .setParameter("m", car.getModel())
                .setParameter("s", car.getSeries());

        return query.getSingleResult();
    }

}