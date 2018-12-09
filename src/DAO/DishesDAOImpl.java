package DAO;

import Entity.Dishes;
import Entity.EntityClass;
import Entity.Item;

import javax.persistence.NoResultException;
import java.util.List;

public class DishesDAOImpl extends DAOImpl implements DishesDAO
{
    public DishesDAOImpl()
    {
        className = Dishes.class.getCanonicalName();
    }

    @Override
    public List findByItem(Item item)
    {
        return findByField("item_id", item.getId());
    }

    @Override
    public List findByQuantity(int quantity)
    {
        return findByField("quantity", quantity);
    }

    @Override
    public EntityClass findByFields(EntityClass dishes)
    {
        if(dishes instanceof Dishes)
        {
            checkOpen();
            Object ret;
            entityManager.getTransaction().begin();
            try
            {
                ret = entityManager.createQuery(String.format("from %s where quantity=%d and item_id=%d",
                        className, ((Dishes) dishes).getQuantity(), ((Dishes) dishes).getItem().getId())).getSingleResult();
            }
            catch (NoResultException ex)
            {
                return null;
            }
            finally
            {
                entityManager.close();
            }
            return (Dishes)ret;
        }
        return null;
    }
}
