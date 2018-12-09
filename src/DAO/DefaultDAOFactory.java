package DAO;

public class DefaultDAOFactory extends DAOFactory
{
    @Override
    public DAO getDefaultDAO()
    {
        return new DAOImpl();
    }

    @Override
    public ItemsDAO getItemsDAO()
    {
        return new ItemsDAOImpl();
    }

    @Override
    public OfficiantsDAO getOfficiantsDAO()
    {
        return new OfficiantsDAOImpl();
    }

    @Override
    public OrdersDAO getOrdersDAO()
    {
        return new OrdersDAOImpl();
    }

    @Override
    public DishesDAO getDishesDAO()
    {
        return new DishesDAOImpl();
    }


}
