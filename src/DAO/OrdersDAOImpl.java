package DAO;

import Entity.EntityClass;
import Entity.Officiant;
import Entity.Order;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class OrdersDAOImpl extends DAOImpl implements OrdersDAO
{

    public OrdersDAOImpl()
    {
        this.className = Order.class.getCanonicalName();
    }

    @Override
    public List findByDate(LocalDate date)
    {
        return findByField("date", date);
    }

    @Override
    public List findByOfficiant(Officiant officiant)
    {
        return findByField("officiant_id", officiant.getId());
    }

    @Override
    public Order findByID(int id)
    {
        return entityManager.find(Order.class, id);
    }


    public Order findByFields(EntityClass order)
    {
        if(order instanceof Order)
        {
            checkOpen();
            Object ret;
            entityManager.getTransaction().begin();
            try
            {
                ret = entityManager.createQuery(String.format("from %s where date='%s' and officiant_id=%d",
                        className, ((Order) order).getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                        ((Order) order).getOfficiant().getId())).getSingleResult();
            }
            catch (NoResultException ex)
            {
                return null;
            }
            finally
            {
                entityManager.close();
            }
            return (Order)ret;
        }
        return null;
    }
}
