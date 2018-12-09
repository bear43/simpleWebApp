package DAO;

import Entity.EntityClass;
import Entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DAOImpl implements DAO
{
    protected String className;

    protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sample");

    protected EntityManager entityManager = entityManagerFactory.createEntityManager();

    public DAOImpl() {}

    public DAOImpl(String className)
    {
        this.className = className;
    }

    protected void checkOpen()
    {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen())
            entityManagerFactory = Persistence.createEntityManagerFactory("sample");
        if(entityManager == null || !entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Object read(EntityClass obj)
    {
        checkOpen();
        return entityManager.find(obj.getClass(), obj.getId());
    }

    @Override
    public boolean delete(EntityClass obj)
    {
        checkOpen();
        try
        {
            entityManager.getTransaction().begin();
            entityManager.remove(obj);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex)
        {
            entityManager.close();
            return false;
        }
        return true;
    }

    @Override
    public void save(EntityClass obj)
    {
        //EntityClass o = findByFields(obj);
        //if(o != null) return;
        checkOpen();
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void close()
    {
        checkOpen();
        entityManager.close();
        //if(entityManagerFactory.isOpen()) entityManagerFactory.close();
    }

    @Override
    public Object findByID(int id)
    {
        return findByField("id", id);
    }

    @Override
    public List findByField(String field, String value)
    {
        checkOpen();
        List ret;
        entityManager.getTransaction().begin();
        try
        {
            ret = entityManager.createQuery(String.format("from %s where %s='%s'", className, field, value)).getResultList();
        }
        catch (NoResultException ex)
        {
            return null;
        }
        finally
        {
            entityManager.close();
        }
        return ret;
    }

    @Override
    public List findByField(String field, int value)
    {
        checkOpen();
        List ret;
        entityManager.getTransaction().begin();
        try
        {
            ret = entityManager.createQuery(String.format("from %s where %s=%d", className, field, value)).getResultList();
        }
        catch (NoResultException ex)
        {
            return null;
        }
        finally
        {
            entityManager.close();
        }
        return ret;
    }

    @Override
    public List findByField(String field, double value)
    {
        checkOpen();
        List ret;
        entityManager.getTransaction().begin();
        try
        {
            ret = entityManager.createQuery(String.format("from %s where %s=%f", className, field, value).replace(',', '.')).getResultList();
        }
        catch (NoResultException ex)
        {
            return null;
        }
        finally
        {
            entityManager.close();
        }
        return ret;
    }

    @Override
    public List findByField(String field, LocalDate value)
    {
        return findByField(field, value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Override
    public EntityClass findByFields(EntityClass obj)
    {
        return null;
    }


    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }
}
