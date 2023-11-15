/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author Laboratorio
 */
public class PaisNegocio 
{
   private String nombre;
   private String continente;
   private int codigoPais;
   private int poblacion;
   
   public PaisNegocio()
   {
       
   }
   
   public String insertar(int codigoPais,String nombre, String continente, int poblacion)
   {
       if(poblacion >0)
       {
           return "OK";
       }
       else
       {
           return "";
       }
   }
    
}
