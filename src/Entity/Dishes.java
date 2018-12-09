package Entity;

import DAO.*;
import Util.Saveable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Dishes implements Saveable, Serializable, EntityClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToMany
    private List<Order> orders = new ArrayList<Order>();
    private int quantity;
    @ManyToOne
    private Item item;

    public Dishes(List<Order> orders, Item item, int quantity)
    {
        this.orders = orders;
        this.item = item;
        this.quantity = quantity;
    }

    public Dishes(Order order, Item item, int quantity)
    {
        orders.add(order);
        this.item = item;
        this.quantity = quantity;
    }

    public Dishes()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> order)
    {
        this.orders = order;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Item getItem()
    {
        return item;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Dishes &&
                ((Dishes) obj).id == id &&
                ((Dishes) obj).orders.equals(orders) &&
                ((Dishes) obj).quantity == quantity &&
                ((Dishes) obj).item.equals(item);
    }

    @Override
    public int hashCode()
    {
        return id ^ quantity ^ orders.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format("Order:\n%s\nItem:\n%s\nQuantity: %d", orders, item, quantity);
    }

    @Override
    public void save()
    {
        DAO dao = DAOFactory.getDAOFactory().getDishesDAO();
        EntityClass dishes = dao.findByFields(this);
        if(dishes != null)
            this.id = dishes.getId();
        else
            dao.save(this);
        dao.close();
    }
}
