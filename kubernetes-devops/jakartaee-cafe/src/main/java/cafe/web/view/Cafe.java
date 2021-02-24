package cafe.web.view;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import cafe.model.entity.Coffee;

@Named
@SessionScoped
public class Cafe implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final Logger logger =
      Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  private String SERVICE_URI = "http://localhost:9080/jakartaee-cafe/rest/coffees";
  private transient Client client;

  @NotNull @NotEmpty protected String name;
  @NotNull protected Double price;
  protected List<Coffee> coffeeList;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public List<Coffee> getCoffeeList() {
    return coffeeList;
  }

  @PostConstruct
  private void init() {
    try {
      this.client = ClientBuilder.newClient();
      this.getAllCoffees();
    } catch (IllegalArgumentException | NullPointerException | WebApplicationException ex) {
      logger.severe("Processing of HTTP response failed.");
      ex.printStackTrace();
    }
  }

  private void getAllCoffees() {
    this.coffeeList =
        this.client
            .target(this.SERVICE_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(new GenericType<List<Coffee>>() {});
  }

  public void addCoffee() {
    Coffee coffee = new Coffee(this.name, this.price);
    this.client.target(SERVICE_URI).request(MediaType.APPLICATION_JSON).post(Entity.json(coffee));
    this.name = null;
    this.price = null;
    this.getAllCoffees();
  }

  public void removeCoffee(String coffeeId) {
    this.client.target(SERVICE_URI).path(coffeeId).request().delete();
    this.getAllCoffees();
  }
}
