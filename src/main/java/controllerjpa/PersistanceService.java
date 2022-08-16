package controllerjpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistanceService {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
    private PersistanceService(){

    }

    public static EntityManagerFactory getEmf(){
        return emf;
    }
}
