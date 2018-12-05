package Entity;

import javax.persistence.*;

@Entity

public class Dishes
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Item item;
    private int quantity;

    public Dishes(Item item, int quantity)
    {
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

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}
