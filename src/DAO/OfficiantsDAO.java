package DAO;

import Entity.Officiant;

public interface OfficiantsDAO extends DAO
{
    Officiant findByName(String firstname, String secondname);
    Officiant findByID(int id);
}
