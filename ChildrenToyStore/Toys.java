package ChildrenToyStore;

import java.util.UUID;

public class Toys {

    private final UUID id = UUID.randomUUID();
    private final String nameToy;
    private int quantity = 0;
    private int lossRateToy = 0;

    public Toys(String nameToy){
        this.nameToy = nameToy;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getNameToy() {
        return nameToy;
    }
    public int getLossRateToy() {
        return lossRateToy;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setLossRateToy(int lossRateToy) {
        this.lossRateToy = lossRateToy;
    }

    public int addTenPercent() {
        return quantity  =  100 / getLossRateToy() ;
    }

    @Override
    public String toString() {
        return "Toys{" +
                "id=" + id +
                ", nameToy='" + nameToy + '\'' +
                ", quantity=" + quantity +
                ", lossRateToy=" + lossRateToy +
                '}';
    }
}
