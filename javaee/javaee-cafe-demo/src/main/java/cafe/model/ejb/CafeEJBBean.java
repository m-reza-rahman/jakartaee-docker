package cafe.model.ejb;

import cafe.model.entity.Coffee;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.ejb.Local;
/**
 * CafeEJBBean is a stateless session bean that manages the entity Coffee .
 */
@Stateless(name="cafeEJBBean")
public class CafeEJBBean {

	private static final Logger logger =
			Logger.getLogger( "cafe.model.ejb.CafeEJBBean" );
	@PersistenceContext(unitName="javaEEDockerDemo")
	private EntityManager em;

	public List<Coffee> getAllCoffees() {
		logger.log( Level.INFO, "Findind all coffees");
		return this.em.createNamedQuery( "findAllCoffees" ).getResultList();
	}

	public Coffee persistCoffee(Coffee coffee) {
		logger.log( Level.INFO, "Persisting the new coffee ", coffee );
		this.em.persist( coffee );
		return coffee;
	}

	public boolean removeCoffeeById(Long coffeeId) {
		logger.log( Level.INFO, "Removing a coffee ", coffeeId );
		Coffee coffee = em.find(Coffee.class, coffeeId);
		this.em.remove(coffee);
		return true;
	}

	 public Coffee findCoffeeById(Long coffeeId) {
		 logger.log( Level.INFO, "Finding the coffee with id ", coffeeId );
	 	return this.em.find(Coffee.class, coffeeId);
	}
}
