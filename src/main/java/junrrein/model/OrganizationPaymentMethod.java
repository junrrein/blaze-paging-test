package junrrein.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrganizationPaymentMethod {

    @EmbeddedId
    private OrganizationPaymentMethodPK id;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PaymentMethod paymentMethod;

    private Boolean enabled;


    public OrganizationPaymentMethod() {
    }

    public OrganizationPaymentMethod(OrganizationPaymentMethodPK id, Boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }


    public OrganizationPaymentMethodPK getId() {
        return id;
    }

    public void setId(OrganizationPaymentMethodPK id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
