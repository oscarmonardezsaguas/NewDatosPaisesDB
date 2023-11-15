/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Laboratorio
 */
public class Ciudad extends Pais 
{
    
    private String nombreCiudad;
    private String paisPertenece;
    private String distrito;
    private int poblacionCiudad;

    public Ciudad() 
    {
    }

    public Ciudad(String nombreCiudad, String paisPertenece, String distrito, int poblacionCiudad, String nombre, String continente, String Ciudades, String region, float superficie, int añoIndp, int poblacion, float expVida, float proNaciBruto, String tipoGob, String jefeEstado, String cityCapital, int codePais, String idioma) 
    {
        super(nombre, continente, Ciudades, region, superficie, añoIndp, poblacion, expVida, proNaciBruto, tipoGob, jefeEstado, cityCapital, codePais, idioma);
        this.nombreCiudad = nombreCiudad;
        this.paisPertenece = paisPertenece;
        this.distrito = distrito;
        this.poblacionCiudad = poblacionCiudad;
    }

    public Ciudad(String nombreCiudad, String paisPertenece, String distrito, int poblacionCiudad) {
        this.nombreCiudad = nombreCiudad;
        this.paisPertenece = paisPertenece;
        this.distrito = distrito;
        this.poblacionCiudad = poblacionCiudad;
    }
    
 
    public String getNombreCiudad() 
    {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) 
    {
        this.nombreCiudad = nombreCiudad;
    }

    public String getPaisPertenece() 
    {
        return paisPertenece;
    }

    public void setPaisPertenece(String paisPertenece) 
    {
        this.paisPertenece = paisPertenece;
    }

    public String getDistrito() 
    {
        return distrito;
    }

    public void setDistrito(String distrito) 
    {
        this.distrito = distrito;
    }

    public int getPoblacionCiudad() 
    {
        return poblacionCiudad;
    }

    public void setPoblacionCiudad(int poblacionCiudad) 
    {
        this.poblacionCiudad = poblacionCiudad;
    }

    @Override
    public String toString() 
    {
        return "Ciudad{" + "nombreCiudad=" + nombreCiudad + ", paisPertenece=" + paisPertenece + ", distrito=" + distrito + ", poblacionCiudad=" + poblacionCiudad + '}';
    }
    
    
    
    
    
    
}
