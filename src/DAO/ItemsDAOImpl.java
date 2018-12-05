package DAO;

import Entity.Item;

import java.util.Collection;

public class ItemsDAOImpl extends DAOImpl implements ItemsDAO
{
    @Override
    public Collection<Item> findByName(String name)
    {
        return null;
    }

    @Override
    public Collection<Item> findByCost(double cost)
    {
        return null;
    }

    @Override
    public Item findByID(int id)
    {
        checkOpen();
        Object ret;
        entityManager.getTransaction().begin();
        ret = entityManager.createQuery(String.format("from Item where id=%d", id)).getSingleResult();
        entityManager.close();
        return (Item)ret;
    }
}
