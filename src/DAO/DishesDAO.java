package DAO;

import Entity.Item;

import java.util.List;

public interface DishesDAO extends DAO
{
    List findByItem(Item item);
    List findByQuantity(int quantity);
}
