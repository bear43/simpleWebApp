package DAO;

import Entity.EntityClass;
import Entity.Item;

import javax.persistence.NoResultException;
import java.util.ArrayList;
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
    @Override
    @SuppressWarnings("uncheked")
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

    @Override
    @SuppressWarnings("uncheked")
    public List<Item> findByDescription(String description)
    {
        return (List<Item>)findByField("description", description);
    }

    @Override
    public List<Item> joinResults(String name, String description, double cost)
    {
        List<Item> n = null, d = null, c = null, result = new ArrayList<>();
        if(name != null && !name.isEmpty() &&
                description != null && !description.isEmpty() &&
                cost != 0)
            result.add(findByFields(new Item(name, description, cost)));
        else
        {
            if (name != null && !name.isEmpty())
                n = findByName(name);
            if (description != null && !description.isEmpty())
                d = findByDescription(description);
            if (cost != 0)
                c = findByCost(cost);
            if (name != null && !name.isEmpty() && d != null && !d.isEmpty() && cost == 0)//name & desc
                for (Item dItem : d)
                    if (dItem.getName().equals(name))
                        result.add(dItem);
            if (name != null && !name.isEmpty() && c != null && !c.isEmpty() && (description == null || description.isEmpty()))//name & cost
                for (Item cItem : c)
                    if (cItem.getName().equals(name))
                        result.add(cItem);
            if (d != null && !d.isEmpty() && cost != 0 && (name == null || name.isEmpty()))//desc & cost
                for (Item dItem : d)
                    if (dItem.getCost() == cost)
                        result.add(dItem);
            if (name != null && !name.isEmpty() && (d == null || d.isEmpty()) && cost == 0)//name
                result.addAll(n);
            if ((name == null || name.isEmpty()) && (d != null && !d.isEmpty()) && cost == 0)//desc
                result.addAll(d);
            if ((name == null || name.isEmpty()) && (d == null || d.isEmpty()) && cost != 0 && c != null && !c.isEmpty())//cost
                result.addAll(c);
        }
        return result;
    }
}
