package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("config.txt"));

        String daoClassName = sc.nextLine();
        Class cDAO = Class.forName(daoClassName);
        IDao d=(IDao) cDAO.newInstance();

        String metierClassName = sc.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier=(IMetier) cMetier.getConstructor(IDao.class).newInstance(d);

        System.out.println("res= "+metier.calcul());

    }
}
