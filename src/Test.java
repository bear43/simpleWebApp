import DAO.DAOFactory;
import DAO.ItemsDAO;
import Entity.Item;
import Entity.Officiant;
import Entity.Order;
import Servlet.servletExample;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class Test
{
    public static void main(String[] args)
    {
        /*ItemsDAO dao = DAOFactory.getDAOFactory().getItemsDAO();
        List<Item> items = new ArrayList<>();
        Map<String, String[]> map = new HashMap<>();
        map.put("name", new String[] {"Salad", "Putinka"});
        Item it;
        try
        {
            if (map.containsKey("id"))
            {
                String[] ids = map.get("id");
                for(String id : ids)
                {
                    it = (Item)dao.findByID(Integer.parseInt(id));
                    if(it != null) items.add(it);
                }
            }
            else
            {
                String[] names = map.get("name");
                String[] descriptions = map.get("description");
                String[] costs = map.get("cost");
                if(names == null) names = new String[]{null};
                if(descriptions == null) descriptions = new String[]{null};
                if(costs == null) costs = new String[]{null};
                for(String n : names)
                    for(String d : descriptions)
                        for(String c : costs)
                            items.addAll(dao.joinResults(n, d, c == null ? 0 : Double.parseDouble(c)));
            }
            if(items.isEmpty())
                items.addAll(dao.findAll());
            for(Item i : items)
                if(i != null) System.out.println(i.toString());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }*/
    }
}
