package Entity;

import DAO.*;
import Util.Saveable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Officiant implements Saveable, Serializable, EntityClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String secondname;
    @OneToMany(mappedBy = "officiant")
    private Set<Order> orders;

    public Officiant(String firstname, String secondname)
    {
        this.firstname = firstname;
        this.secondname = secondname;
    }

    public Officiant()
    {

    }

    public Officiant(String firstname, String secondname, Set<Order> orders)
    {
        this.firstname = firstname;
        this.secondname = secondname;
        this.orders = orders;
    }

    protected Officiant(int id, String firstname, String secondname, Set<Order> orders)
    {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.orders = orders;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getSecondname()
    {
        return secondname;
    }

    public void setSecondname(String secondname)
    {
        this.secondname = secondname;
    }

    public Set<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(Set<Order> orders)
    {
        this.orders = orders;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    protected void setId()
    {

    }

    public boolean contains(Order order)
    {
        return orders.contains(order);
    }

    public void add(Order order)
    {
        orders.add(order);
    }

    public Order remove(Order order)
    {
        return orders.remove(order) ? order : null;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Officiant &&
                ((Officiant) obj).id == id &&
                ((Officiant) obj).firstname.equals(firstname) &&
                ((Officiant) obj).secondname.equals(secondname) &&
                ((Officiant) obj).orders.equals(orders);
    }

    @Override
    public int hashCode()
    {
        return id ^ firstname.hashCode() ^ secondname.hashCode() ^ orders.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format("Firstname: %s\nSecondname: %s", firstname, secondname);
    }

    @Override
    public void save()
    {
        DAO dao = DAOFactory.getDAOFactory().getOfficiantsDAO();
        EntityClass officiant = dao.findByFields(this);
        if(officiant != null)
            this.id = officiant.getId();
        else
            dao.save(this);
        dao.close();
    }
}
