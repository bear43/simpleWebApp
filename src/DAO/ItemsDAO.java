package DAO;

import Entity.Item;

import java.util.Collection;

public interface ItemsDAO extends DAO
{
    Collection<Item> findByName(String name);
    Collection<Item> findByCost(double cost);
    Item findByID(int id);
}
