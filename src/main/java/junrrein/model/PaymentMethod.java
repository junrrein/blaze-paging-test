package junrrein.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("unused")
@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;


    public PaymentMethod() {
    }

    public PaymentMethod(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
