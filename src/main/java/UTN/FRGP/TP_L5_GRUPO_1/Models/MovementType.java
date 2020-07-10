package UTN.FRGP.TP_L5_GRUPO_1.Models;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;

import javax.persistence.*;

@Entity
public class MovementType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Transferencia propia cuenta
     * Transferencia cuenta terceros
     * Compra dolares
     * Venta dolares
     * Transfiere dolares
     */
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @OneToOne
    @JoinColumn
    private CurrencyType currencyType;

    public MovementType() {}

    public MovementType(MovementTypeEnum movementTypeEnum, CurrencyType currencyType) {
        this.setMovementType(movementTypeEnum);
        this.setCurrencyType(currencyType);
    }

    public MovementTypeEnum getName() {
        return MovementTypeEnum.valueOf(this.name);
    }

    public void setMovementType(MovementTypeEnum movementTypeEnum) {
        this.name = movementTypeEnum.toString();
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return this.name.equals("TRANSFER_OWN_ACCOUNT")
                ? "Transferencia en cuenta propia"
                : this.name.equals("TRANSFER_EXTERNAL_ACCOUNT")
                ? "Transferencia a cuenta extenera"
                : this.name.equals("BUY_DOLLARS")
                ? "Compra de dólares"
                : this.name.equals("SELL_DOLLARS")
                ? "Venta de dólares"
                : "Aprobación de Préstamo";
    }
}
