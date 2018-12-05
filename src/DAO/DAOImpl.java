package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOImpl implements DAO
{
    protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sample");

    protected EntityManager entityManager = entityManagerFactory.createEntityManager();

    protected void checkOpen()
    {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen())
            Persistence.createEntityManagerFactory("sample");
        if(entityManager == null || !entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Object read(Object obj)
    {
        checkOpen();
        return entityManager.find(obj.getClass(), obj);
    }

    @Override
    public boolean delete(Object obj)
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
    public void save(Object obj)
    {
        checkOpen();
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close()
    {
        checkOpen();
        if(entityManagerFactory.isOpen()) entityManagerFactory.close();
    }
}
