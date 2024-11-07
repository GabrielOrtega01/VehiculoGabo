package com.mongodbvehiculosgabo.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pago")
public class Pago {
    @Id
    private String id;

    @DBRef
    private Reserva reserva; // Relación con Reserva

    private String monto;
    private String fechaFin;
    private String fechaPago;
    private String metodoPago;

    public Pago() {
        super();
    }

    public Pago(String id, Reserva reserva, String monto, String fechaFin, String fechaPago, String metodoPago) {
        super();
        this.id = id;
        this.reserva = reserva;
        this.monto = monto;
        this.fechaFin = fechaFin;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
