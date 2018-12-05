package DAO;

import Entity.Officiant;

public class OfficiantsDAOImpl extends DAOImpl implements OfficiantsDAO
{
    @Override
    public Officiant findByName(String firstname, String secondname)
    {
        return null;
    }

    @Override
    public Officiant findByID(int id)
    {
        checkOpen();
        Object ret;
        entityManager.getTransaction().begin();
        ret = entityManager.createQuery(String.format("from Officiant where id=%d", id)).getSingleResult();
        entityManager.close();
        return (Officiant) ret;
    }
}
