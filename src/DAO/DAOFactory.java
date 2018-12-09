package DAO;

public abstract class DAOFactory
{
    protected DAOFactory()
    {

    }

    public static DAOFactory getDAOFactory()
    {
        return new DefaultDAOFactory();
    }

    public abstract DAO getDefaultDAO();

    public abstract ItemsDAO getItemsDAO();

    public abstract OfficiantsDAO getOfficiantsDAO();

    public abstract OrdersDAO getOrdersDAO();

    public abstract DishesDAO getDishesDAO();
}
