package Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Entity
@Table(name="_Order")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate date;
    @ManyToOne
    private Officiant officiant;
    @OneToMany(mappedBy = "item")
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

    public Order(LocalDate date, Officiant officiant, List<Dishes> dishes)
    {
        this.date = date;
        this.officiant = officiant;
        this.dishes = dishes;
    }

    protected Order(int id, LocalDate date, Officiant officiant, List<Dishes> dishes)
    {
        this.id = id;
        this.date = date;
        this.officiant = officiant;
        this.dishes = dishes;
    }

    public int getId()
    {
        return id;
    }

    protected void setId(int id)
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
        dishes.add(new Dishes(item, quantity));
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
}
