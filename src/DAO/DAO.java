package DAO;

import Entity.EntityClass;

import java.time.LocalDate;
import java.util.List;

public interface DAO
{
    Object read(EntityClass obj);
    boolean delete(EntityClass obj);
    void save(EntityClass obj);
    void close();
    /**
     * Before execute this method you need to set a class name by setClassName(String className) method
     * @param id Which id searching in a DB
     * @return Desired object
     */
    Object findByID(int id);
    List findByField(String field, String value);
    List findByField(String field, int value);
    List findByField(String field, double value);
    List findByField(String field, LocalDate value);
    EntityClass findByFields(EntityClass obj);
    List findAll();
}
