package Entity;

import DAO.*;
import Util.Saveable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Util.SaveManager.saveList;


@Entity
@Table(name="_Order")
public class Order implements Saveable, Serializable, EntityClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate date;
    @ManyToOne
    private Officiant officiant;
    @ManyToMany(mappedBy = "orders")
    private List<Dishes> dishes;

    private void checkItem(Item item) throws Exception
    {
        for(Dishes d : dishes)
            if(d.getItem().equals(item)) return;
        throw new Exception("[remove] No such item");
    }

    public Order()
    {

    }

    protected void assignDishes() throws Exception
    {
        if(dishes == null) throw new Exception("[Order.class] Cannot assign null pointer dishes");
        for(Dishes dish : dishes)
            dish.getOrders().add(this);
    }

    public Order(LocalDate date, Officiant officiant, List<Dishes> dishes) throws Exception
    {
        this.date = date;
        this.officiant = officiant;
        this.dishes = dishes;
        assignDishes();
    }

    public Order(LocalDate date, Officiant officiant) throws Exception
    {
        this.date = date;
        this.officiant = officiant;
        this.dishes = new ArrayList<Dishes>();
    }

    protected Order(int id, LocalDate date, Officiant officiant, List<Dishes> dishes) throws Exception
    {
        this.id = id;
        this.date = date;
        this.officiant = officiant;
        this.dishes = dishes;
        assignDishes();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public Officiant getOfficiant()
    {
        return officiant;
    }

    public void setOfficiant(Officiant officiant)
    {
        this.officiant = officiant;
    }

    public List<Dishes> getDishes()
    {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes)
    {
        this.dishes = dishes;
    }

    public void addItem(Item item) throws Exception
    {
        addItems(item, 1);
    }

    public void addItems(Item item, int quantity) throws Exception
    {
        Dishes d;
        for(int i = 0; i < dishes.size(); i++)
        {
            d = dishes.get(i);
            if(d.getItem().equals(item))
            {
                d.setQuantity(d.getQuantity() + quantity);
                return;
            }
        }
        dishes.add(new Dishes(this, item, quantity));
    }

    public void remove(Item item) throws Exception
    {
        checkItem(item);
        Dishes d;
        for(int i = 0; i < dishes.size(); i++)
        {
            d = dishes.get(i);
            if (d.getItem().equals(item))
            {
                dishes.remove(d);
                return;
            }
        }
    }

    public void setQuantity(Item item, int quantity) throws Exception
    {
        checkItem(item);
        Dishes d;
        for(int i = 0; i < dishes.size(); i++)
        {
            d = dishes.get(i);
            if (d.getItem().equals(item))
            {
                d.setQuantity(quantity);
                if (quantity == 0)
                    dishes.remove(d);
                return;
            }
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Order &&
                ((Order) obj).id == id &&
                ((Order) obj).date.equals(date) &&
                ((Order) obj).dishes.equals(dishes) &&
                ((Order) obj).officiant.equals(officiant);
    }

    @Override
    public int hashCode()
    {
        return id ^ date.hashCode() ^ dishes.hashCode() ^ officiant.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format("Date: %s\nOfficiant: %s\nDishes:\n%s", date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('-', '.'), officiant, dishes);
    }

    @Override
    public void save() throws Exception
    {
        officiant.save();
        for(Dishes dishes : this.dishes)
            dishes.getItem().save();
        DAO dao = DAOFactory.getDAOFactory().getOrdersDAO();
        dao.save(this);
        dao.close();
        saveList(dishes);
    }
}
