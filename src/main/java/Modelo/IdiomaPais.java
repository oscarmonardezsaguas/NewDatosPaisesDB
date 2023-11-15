/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Laboratorio
 */
public class IdiomaPais extends Pais
{
    private String idiomaPais;
    private String isOfficial;
    private float porcetajeIdioma;

    public IdiomaPais() 
    {
    }

    public IdiomaPais(String idiomaPais, String isOfficial, float porcetajeIdioma, String nombre, String continente, String Ciudades, String region, float superficie, int añoIndp, int poblacion, float expVida, float proNaciBruto, String tipoGob, String jefeEstado, String cityCapital, int codePais, String idioma) 
    {
        super(nombre, continente, Ciudades, region, superficie, añoIndp, poblacion, expVida, proNaciBruto, tipoGob, jefeEstado, cityCapital, codePais, idioma);
        this.idiomaPais = idiomaPais;
        this.isOfficial = isOfficial;
        this.porcetajeIdioma = porcetajeIdioma;
    }

    public String getIdiomaPais() 
    {
        return idiomaPais;
    }

    public void setIdiomaPais(String idiomaPais) 
    {
        this.idiomaPais = idiomaPais;
    }

    public String getIsOfficial() 
    {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) 
    {
        this.isOfficial = isOfficial;
    }

    public float getPorcetajeIdioma() 
    {
        return porcetajeIdioma;
    }

    public void setPorcetajeIdioma(float porcetajeIdioma) 
    {
        this.porcetajeIdioma = porcetajeIdioma;
    }

    @Override
    public String toString() 
    {
        return "IdiomaPais{" + "idiomaPais=" + idiomaPais + ", isOfficial=" + isOfficial + ", porcetajeIdioma=" + porcetajeIdioma + '}';
    } 
}
