package infrastructure.persistence;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoFinanceiroPU");
	
	private JpaUtil() {
		
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		
		return emf;
	}
	
	public static void closeEntityManagerFactory() {
		emf.close();
	}
	
	
}
