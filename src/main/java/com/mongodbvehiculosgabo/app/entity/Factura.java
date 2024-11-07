package com.mongodbvehiculosgabo.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "factura")
public class Factura {
    @Id
    private String id;

    @DBRef
    private Pago pago; // Relación con Pago

    private String detalles;

    public Factura() {
        super();
    }

    public Factura(String id, Pago pago, String detalles) {
        super();
        this.id = id;
        this.pago = pago;
        this.detalles = detalles;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
