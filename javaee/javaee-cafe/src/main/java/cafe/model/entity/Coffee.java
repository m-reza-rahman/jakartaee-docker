package cafe.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Coffee")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQuery(name = "findAllCoffees",
query = "SELECT o FROM Coffee o")
public class Coffee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Coffee_Generator")
    @SequenceGenerator(name="Coffee_Generator", sequenceName = "COFFEE_SEQ", allocationSize=1)
    private Long id;
    protected String name;
    protected Double price;

    public Coffee() {
    }

    public Coffee(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param birthday new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param price new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Coffee)) {
            return false;
        }
        Coffee other = (Coffee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cafe.entity.Coffee[id=" + id + ", name=" +name + ", price=" + price+"]";
    }
}
