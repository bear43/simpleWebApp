import Entity.Item;
import Entity.Officiant;
import Entity.Order;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test
{
    public static void main(String[] args)
    {
        /*PrintWriter pw = new PrintWriter(System.out);
        try
        {
            Random r = new Random();
            Item[] items = new Item[]{
                    new Item("Hookah-buba", "Shisha with bubble gum taste", 777),
                    new Item("Borsch", "Soup with sour cream and Russian(Ukranian(Slavic)) soul", 50),
                    new Item("Salad", "Cucumber + tomato", 25),
                    new Item("Putinka", "The powerful drink of the mother Russia", 300)
            };
            Officiant[] officiants = new Officiant[]{
                    new Officiant("Odmen", "S4"),
                    new Officiant("Io", "Asakura"),
                    new Officiant("Naruto", "Udzumaki"),
                    new Officiant("Harry", "Povar"),
                    new Officiant("Sergey", "Brin")
            };
            List<Order> orders = new ArrayList<>();
            orders.add(new Order(LocalDate.now().plusDays(r.nextInt(366)),
                    officiants[r.nextInt(officiants.length)]));
            orders.add(new Order(LocalDate.now().plusDays(r.nextInt(366)),
                    officiants[r.nextInt(officiants.length)]));
            orders.forEach((x) ->
            {
                try
                {
                    x.addItems(items[r.nextInt(items.length)], Math.abs(r.nextInt(20)));
                    x.save();
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            });
            pw.println("dao closed");
        } catch (Exception ex)
        {
            pw.println("FUCK, EXCEPTION");
            ex.printStackTrace(pw);
            pw.flush();
        } finally
        {
            pw.close();
        }*/
    }
}
