package cafe.model.resource;

import cafe.model.ejb.CafeEJBBean;
import cafe.model.entity.Coffee;

import java.net.URI;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * REST Web Service
 *
 */
@Named
@Path("cafeRS")
public class CafeResource {

	/** Creates a new instance of CafeResource */
	public CafeResource() {
	}

	private static final Logger logger =
			Logger.getLogger( "cafe.model.resource.cafeRS" );

	@EJB
	private CafeEJBBean cafeEJBBean;

	/**
	 * Get all Coffees XML
	 * <code>CoffeeType</code>
	 *
	 * @return List<Coffee>
	 */
	@GET
	@Path("all")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Coffee> getAllCoffees() {
		List<Coffee> coffeeList = null;
		try {
			coffeeList = this.cafeEJBBean.getAllCoffees();
			if (coffeeList == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					   "Error calling getAllCoffees()",
					   new Object[]{ex.getMessage()});
		}
		return coffeeList;
	}

	/**
	 * createCoffee method based on
	 * <code>CoffeeType</code>
	 *
	 * @param coffee
	 * @return Response URI for the Coffee added
	 * @see Coffee.java
	 */
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createCoffee(Coffee coffee) {
		try {
			coffee = this.cafeEJBBean.persistCoffee(coffee);
			return Response.created(URI.create("/" + coffee.getId())).build();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					   "Error creating coffee: " + coffee.toString(),
					   new Object[]{coffee.getId(), e.getMessage()});
			throw new WebApplicationException(e,
											  Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get coffee XML
	 *
	 * @param coffeeId
	 * @return Coffee
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Coffee getCoffeeById(@PathParam("id") Long coffeeId) {
		Coffee coffee = null;
		try {
			coffee = this.cafeEJBBean.findCoffeeById(coffeeId);
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					   "Error calling getCoffeeById() for coffeeIdId {0}. {1}",
					   new Object[]{coffeeId, ex.getMessage()});
		}
		return coffee;
	}


	/**
	 * Delete a coffee
	 *
	 * @param coffeeId
	 */
	@DELETE
	@Path("{id}")
	public void deleteCoffee(@PathParam("id") Long coffeeId) {
		try {
			if (!this.cafeEJBBean.removeCoffeeById(coffeeId)) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					   "Error calling deleteCoffee() for coffeeId {0}. {1}",
					   new Object[]{coffeeId, ex.getMessage()});
		}
	}
}