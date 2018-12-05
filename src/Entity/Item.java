package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private double cost;

    public Item()
    {

    }

    public Item(String name, String description, double cost)
    {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    protected Item(int id, String name, String description, double cost)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public int getId()
    {
        return id;
    }

    protected void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Item &&
                ((Item) obj).id == id &&
                ((Item) obj).name.equals(name) &&
                ((Item) obj).description.equals(description) &&
                ((Item) obj).cost == cost;
    }

    @Override
    public int hashCode()
    {
        return id ^ name.hashCode() ^ description.hashCode() ^ (int)cost;
    }
}
