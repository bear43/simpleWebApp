package DAO;

public interface DAO
{
    Object read(Object obj);
    boolean delete(Object obj);
    void save(Object obj);
    void close();
}
