package DAO;

import Entity.EntityClass;
import Entity.Officiant;

import javax.persistence.NoResultException;
import java.util.List;

public class OfficiantsDAOImpl extends DAOImpl implements OfficiantsDAO
{

    public OfficiantsDAOImpl()
    {
        this.className = Officiant.class.getCanonicalName();
    }

    @Override
    public Officiant findByName(String firstname, String secondname)
    {
        checkOpen();
        Object ret;
        entityManager.getTransaction().begin();
        try
        {
            ret = entityManager.createQuery(String.format("from %s where firstname='%s' and secondname='%s'",
                    className, firstname, secondname)).getSingleResult();

        }
        catch (NoResultException ex)
        {
            return null;
        }
        finally
        {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        return (Officiant)ret;
    }

    @Override
    public Officiant findByID(int id)
    {
        return entityManager.find(Officiant.class, id);
    }

    public Officiant findByFields(EntityClass officiant)
    {
        return officiant instanceof Officiant ? findByName(((Officiant) officiant).getFirstname(),
                ((Officiant) officiant).getSecondname()) : null;
    }
}
