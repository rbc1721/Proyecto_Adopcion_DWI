
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Eduardo
 */
public class MascotaEncontrada {
    private int codigo;
    private LocalDate fecha;
    private String direccion;
    private String idUduario;
    private int idMascota;
    private String especie;
    private String raza;
    private String color;  
    private String tamanio;
    private String descripcion;
    private String foto;

    public MascotaEncontrada(int codigo, LocalDate fecha, String direccion, String idUduario, int idMascota, String especie, String raza, String color, String tamanio, String descripcion, String foto) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.direccion = direccion;
        this.idUduario = idUduario;
        this.idMascota = idMascota;
        this.especie = especie;
        this.raza = raza;
        this.color = color;
        this.tamanio = tamanio;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getIdUduario() {
        return idUduario;
    }

    public void setIdUduario(String idUduario) {
        this.idUduario = idUduario;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
}
