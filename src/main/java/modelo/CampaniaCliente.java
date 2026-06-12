package modelo;

import java.sql.Timestamp;

public class CampaniaCliente {
    private int idCampaniaCliente;
    private int idCampania;
    private int idCliente;
    private Timestamp fechaAsignacion;
    private String estado;

    public int getIdCampaniaCliente() {
        return idCampaniaCliente;
    }

    public void setIdCampaniaCliente(int idCampaniaCliente) {
        this.idCampaniaCliente = idCampaniaCliente;
    }

    public int getIdCampania() {
        return idCampania;
    }

    public void setIdCampania(int idCampania) {
        this.idCampania = idCampania;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Timestamp getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Timestamp fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
