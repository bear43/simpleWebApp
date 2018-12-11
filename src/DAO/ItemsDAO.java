package DAO;

import Entity.Item;

import java.util.Collection;
import java.util.List;

public interface ItemsDAO extends DAO
{
    List<Item> findByName(String name);
    List<Item> findByCost(double cost);
    List<Item> findByDescription(String description);
    List<Item> joinResults(String name, String description, double cost);
}
