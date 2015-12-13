package com.example.luiseduardo.eparkeletrica.DAOConsumo;
 
import java.io.Serializable;
 

public class ConsumoVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String registro_inicial;
    private String registro_final;
    private String mes;
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }

    public String getRegistro_inicial() {
        return registro_inicial;
    }

    public void setRegistro_inicial(String value) {
        this.registro_inicial = registro_inicial;
    }

    public String getRegistro_final() {
        return registro_final;
    }

    public void setRegistro_final(String value) {
        this.registro_final = registro_final;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String value) {
        this.mes = mes;
    }
}
