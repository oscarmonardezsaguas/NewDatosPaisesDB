/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author Laboratorio
 */
public class CiudadNegocio 
{
    private String nombreCiudad;
    private String paisPertenece;
    private String distrito;
    private int poblacionCiudad;

    public CiudadNegocio() 
    {
        
    }
    
    public String insertar(String nombreCiudad,String paisPertenece, String distrito, int poblacion)
   {
       if(poblacion >=0)
       {
           return "OK";
       }
       else
       {
           return "";
       }
   }

}
