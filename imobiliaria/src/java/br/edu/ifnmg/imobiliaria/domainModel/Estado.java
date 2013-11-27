/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.imobiliaria.domainModel;

/**
 *
 * @author Anderson
 */
public class Estado {
    
    public Estado(){
        this.estados = new String[]{"Selecione", "Acre", "Alagoas", "Amazonas", "Amapa", "Bahia",
            "Ceara", "Distrito Federal", "Espirito Santo", "Goias", "Maranhao", "Minas Gerais", 
            "Mato Grosso do Sul", "Mato Grosso", "Para", "Paraiba", "Pernambuco", "Piaui", "Parana", 
            "Rio de Janeiro", "Rio Grande do Norte", "Rondonia", "Roraima", "Rio Grande do Sul", "Santa Catarina", 
            "Sergipe", "Sao Paulo", "Tocantins"};
    }
    
    private final  String estados [];

    public String[] getEstados() {
        return estados;
    }
    
    public String getEstadoPorId(int id){
        return estados[id];
    }

    @Override
    public String toString() {
        return estados.toString();
    }
    
    
}
