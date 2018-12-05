package DAO;

import Entity.Officiant;
import Entity.Order;

import java.time.LocalDate;
import java.util.Collection;

public interface OrdersDAO extends DAO
{
    Collection<Order> findByDate(LocalDate date);
    Collection<Order> findByOfficiant(Officiant officiant);
    Order findByID(int id);
}
