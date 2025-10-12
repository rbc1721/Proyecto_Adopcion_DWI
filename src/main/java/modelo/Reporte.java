
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Eduardo
 * Buscar una mascota
 */
public class Reporte {
    private int codigo;
    private String tipo;
    private LocalDate fecha;
    private String direccion;
    private String estado;
    private String idUsuario;
    private Integer idMascota;

    public Reporte(String tipo, LocalDate fecha, String direccion, String estado, String idUsuario, int idMascota) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.direccion = direccion;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idMascota = idMascota;
    }
    
    public Reporte(){
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUduario(String idUduario) {
        this.idUsuario = idUduario;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    

}
