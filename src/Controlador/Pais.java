package Controlador;

public class Pais 
{
    //Acá codificará las Características y Propiedades para la clase País.
    private String nombre;
    private String continente;
    private String Ciudades;
    private String region;
    private float superficie;
    private int añoIndp;
    private int poblacion;
    private float expVida;
    private float proNaciBruto;
    private String tipoGob;
    private String jefeEstado;
    private String cityCapital;
    private String codePais;
    private String idioma;

    public Pais(String nombre, String continente, String Ciudades, String region, float superficie, int añoIndp, int poblacion, float expVida, float proNaciBruto, String tipoGob, String jefeEstado, String cityCapital, String codePais, String idioma) 
    {
        this.nombre = nombre;
        this.continente = continente;
        this.Ciudades = Ciudades;
        this.region = region;
        this.superficie = superficie;
        this.añoIndp = añoIndp;
        this.poblacion = poblacion;
        this.expVida = expVida;
        this.proNaciBruto = proNaciBruto;
        this.tipoGob = tipoGob;
        this.jefeEstado = jefeEstado;
        this.cityCapital = cityCapital;
        this.codePais = codePais;
        this.idioma = idioma;
    }

    public Pais(String codePais, String nombre, String continente, int poblacion ) 
    {
        this.nombre = nombre;
        this.continente = continente;
        this.poblacion = poblacion;
        this.codePais = codePais;
    }
    
    public Pais() 
    {
    }

    public Pais(String idioma) {
        this.idioma = idioma;
    }
    
    
    
    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getContinente() 
    {
        return continente;
    }

    public void setContinente(String continente) 
    {
        this.continente = continente;
    }

    public String getCiudades() 
    {
        return Ciudades;
    }

    public void setCiudades(String Ciudades) 
    {
        this.Ciudades = Ciudades;
    }

    public String getRegion() 
    {
        return region;
    }

    public void setRegion(String region) 
    {
        this.region = region;
    }

    public float getSuperficie() 
    {
        return superficie;
    }

    public void setSuperficie(float superficie) 
    {
        this.superficie = superficie;
    }

    public int getAñoIndp() 
    {
        return añoIndp;
    }

    public void setAñoIndp(int añoIndp) 
    {
        this.añoIndp = añoIndp;
    }

    public int getPoblacion() 
    {
        return poblacion;
    }

    public void setPoblacion(int poblacion) 
    {
        this.poblacion = poblacion;
    }

    public float getExpVida() 
    {
        return expVida;
    }

    public void setExpVida(float expVida) 
    {
        this.expVida = expVida;
    }

    public float getProNaciBruto() 
    {
        return proNaciBruto;
    }

    public void setProNaciBruto(float proNaciBruto) 
    {
        this.proNaciBruto = proNaciBruto;
    }

    public String getTipoGob() 
    {
        return tipoGob;
    }

    public void setTipoGob(String tipoGob) 
    {
        this.tipoGob = tipoGob;
    }

    public String getJefeEstado() 
    {
        return jefeEstado;
    }

    public void setJefeEstado(String jefeEstado) 
    {
        this.jefeEstado = jefeEstado;
    }

    public String getCityCapital() 
    {
        return cityCapital;
    }

    public void setCityCapital(String cityCapital) 
    {
        this.cityCapital = cityCapital;
    }

    public String getCodePais() 
    {
        return codePais;
    }

    public void setCodePais(String codePais) 
    {
        this.codePais = codePais;
    }

    public String getIdioma() 
    {
        return idioma;
    }

    public void setIdioma(String idioma) 
    {
        this.idioma = idioma;
    }

    @Override
    public String toString() 
    {
        return "Pais{" + "nombre=" + nombre + ", continente=" + continente + ", Ciudades=" + Ciudades + ", region=" + region + ", superficie=" + superficie + ", a\u00f1oIndp=" + añoIndp + ", poblacion=" + poblacion + ", expVida=" + expVida + ", proNaciBruto=" + proNaciBruto + ", tipoGob=" + tipoGob + ", jefeEstado=" + jefeEstado + ", cityCapital=" + cityCapital + ", codePais=" + codePais + ", idioma=" + idioma + '}';
    }
    
    
}
