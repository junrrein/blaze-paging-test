package junrrein.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrganizationPaymentMethodPK implements Serializable {

    @Column(name = "organization_id")
    private Integer organizationId;

    @Column(name = "payment_method_id")
    private Integer paymentMethodId;


    public OrganizationPaymentMethodPK(){
    }

    public OrganizationPaymentMethodPK(Integer organizationId, Integer paymentMethodId) {
        this.organizationId = organizationId;
        this.paymentMethodId = paymentMethodId;
    }


    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationPaymentMethodPK that = (OrganizationPaymentMethodPK) o;
        return Objects.equals(organizationId, that.organizationId) && Objects.equals(paymentMethodId, that.paymentMethodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, paymentMethodId);
    }
}
