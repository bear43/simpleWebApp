package Entity;

import DAO.*;
import Util.Saveable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Item implements Saveable, Serializable, EntityClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private double cost;
    @OneToMany(mappedBy = "item")
    private List<Dishes> dishes;

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

    public void setId(int id)
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

    public List<Dishes> getDishes()
    {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes)
    {
        this.dishes = dishes;
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

    @Override
    public String toString()
    {
        return String.format("Name: %s\nDescription:\n%s\nCost: %f", name, description, cost);
    }

    @Override
    public void save()
    {
        DAO dao = DAOFactory.getDAOFactory().getItemsDAO();
        EntityClass item = dao.findByFields(this);
        if(item != null)
            this.id = item.getId();
        else
            dao.save(this);
        dao.close();
    }
}
