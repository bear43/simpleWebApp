package DAO;

import Entity.Officiant;
import java.time.LocalDate;
import java.util.List;

public interface OrdersDAO extends DAO
{
    List findByDate(LocalDate date);
    List findByOfficiant(Officiant officiant);
}
