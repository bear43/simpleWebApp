package DAO;

import Entity.Officiant;
import Entity.Order;

import java.time.LocalDate;
import java.util.Collection;

public class OrdersDAOImpl extends DAOImpl implements OrdersDAO
{

    @Override
    public Collection<Order> findByDate(LocalDate date)
    {
        return null;
    }

    @Override
    public Collection<Order> findByOfficiant(Officiant officiant)
    {
        return null;
    }

    @Override
    public Order findByID(int id)
    {
        checkOpen();
        Object ret;
        entityManager.getTransaction().begin();
        ret = entityManager.createQuery(String.format("from _Order where id=%d", id)).getSingleResult();
        entityManager.close();
        return (Order)ret;
    }
}
