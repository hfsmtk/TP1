package ext;

import dao.IDao;

public class DaoImplV2 implements IDao {
    @Override
    public double getData() {
        System.out.println("versin capteurs");
        double t = 12;
        return t;
    }
}
