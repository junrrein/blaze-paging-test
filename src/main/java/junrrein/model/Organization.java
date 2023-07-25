package junrrein.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Organization {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "organization")
    private List<OrganizationPaymentMethod> organizationPaymentMethods;


    public Organization() {
    }

    public Organization(String name) {
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

    public List<OrganizationPaymentMethod> getOrganizationPaymentMethods() {
        return organizationPaymentMethods;
    }

    public void setOrganizationPaymentMethods(List<OrganizationPaymentMethod> organizationPaymentMethods) {
        this.organizationPaymentMethods = organizationPaymentMethods;
    }


    @Override
    public String toString() {
        return "Organization{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
