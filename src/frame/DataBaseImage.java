/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

/**
 *
 * @author ilichh1
 */
public class DataBaseImage {
    private int idImagen;
    private String nombre;
    private String datos;
    
    public DataBaseImage() {
        idImagen = -1;
        nombre = "";
        datos = "";
    }
    
    public DataBaseImage(int id, String nom, String dat) {
        idImagen = id;
        nombre = nom;
        datos = dat;
    }

    /**
     * @return the idImagen
     */
    public int getIdImagen() {
        return idImagen;
    }

    /**
     * @param idImagen the idImagen to set
     */
    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the datos
     */
    public String getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(String datos) {
        this.datos = datos;
    }
}
