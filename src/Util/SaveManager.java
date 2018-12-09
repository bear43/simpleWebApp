package Util;

import DAO.*;

import java.util.Arrays;
import java.util.List;

public class SaveManager
{
    public static void saveArray(Saveable[] array) throws Exception
    {
        if(array == null) throw new Exception("[SaveManager.class] Attempting to save a null pointer array");
        saveList(Arrays.asList(array));
    }

    public static void saveList(List<? extends Saveable> list) throws Exception
    {
        if(list == null) throw new Exception("[SaveManager.class] List is null pointer");
        list.forEach((x) -> {
            try
            {
                x.save();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
    }
}
