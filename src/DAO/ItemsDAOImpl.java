package DAO;

import Entity.EntityClass;
import Entity.Item;

import javax.persistence.NoResultException;
import java.util.List;

public class ItemsDAOImpl extends DAOImpl implements ItemsDAO
{
    public ItemsDAOImpl()
    {
        this.className = Item.class.getCanonicalName();
    }

    @Override
    @SuppressWarnings("uncheked")
    public List<Item> findByName(String name)
    {
        return (List<Item>)findByField("name", name);
    }
    @SuppressWarnings("uncheked")
    @Override
    public List<Item> findByCost(double cost)
    {
        return (List<Item>)findByField("cost", cost);
    }

    public Item findByFields(EntityClass item)
    {
        if(item instanceof Item)
        {
            checkOpen();
            Object ret;
            entityManager.getTransaction().begin();
            try
            {
                ret = entityManager.createQuery(String.format("from %s where name='%s' and description='%s' and cost=%f",
                        className, ((Item) item).getName(), ((Item) item).getDescription(), ((Item) item).getCost()).replace(',', '.')).getSingleResult();
            }
            catch (NoResultException ex)
            {
                return null;
            }
            finally
            {
                entityManager.close();
            }
            return (Item)ret;
        }
        return null;
    }

    @Override
    public Item findByID(int id)
    {
        return entityManager.find(Item.class, id);
    }
}
