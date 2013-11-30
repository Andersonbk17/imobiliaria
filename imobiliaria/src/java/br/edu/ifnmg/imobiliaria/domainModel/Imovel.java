/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.domainModel;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Anderson
 */
@Entity
public class Imovel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enderecoRua;
    private int enderecoNumero;
    private String enderecoBairro;
    private String enderecoCep;
    private String enderecoComplemento;
    private float iptu;
    private float dimensoes;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicioConstrucao;
    @ManyToOne
    TipoDeImovel tipoDeImovel;
    @ManyToOne
    Cidade cidade;
    //private float somaImpostos;
    @ManyToOne
    Cliente clienteProprietario;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataTerminoConstrucao;
    private String latitude;
    private String longitude;
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "imovel")
    private List<Foto> listaFotos;
    @OneToMany
    List<Caracteristica> listaCaracteristicas;
    
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "imovel")
    List<Reforma> listaReformas;

    private boolean ativo;
    
    public Imovel() {
        this.dataInicioConstrucao = null;
        this.tipoDeImovel = null;
        this.cidade = null;
        this.clienteProprietario = null;
        this.dataTerminoConstrucao = null;
        this.listaFotos = new LinkedList<>();
        this.listaCaracteristicas = new LinkedList<>();
        this.listaReformas = new LinkedList<>();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnderecoRua() {
        return enderecoRua;
    }

    public void setEnderecoRua(String enderecoRua) {
        this.enderecoRua = enderecoRua;
    }

    public int getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(int enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public String getEnderecoCep() {
        return enderecoCep;
    }

    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }

    public String getEnderecoComplemento() {
        return enderecoComplemento;
    }

    public void setEnderecoComplemento(String enderecoComplemento) {
        this.enderecoComplemento = enderecoComplemento;
    }

    public float getIptu() {
        return iptu;
    }

    public void setIptu(float iptu) {
        this.iptu = iptu;
    }

    public float getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(float dimensoes) {
        this.dimensoes = dimensoes;
    }

    public Date getDataInicioConstrucao() {
        return dataInicioConstrucao;
    }

    public void setDataInicioConstrucao(Date dataInicioConstrucao) {
        this.dataInicioConstrucao = dataInicioConstrucao;
    }

    public TipoDeImovel getTipoDeImovel() {
        return tipoDeImovel;
    }

    public void setTipoDeImovel(TipoDeImovel tipoDeImovel) {
        this.tipoDeImovel = tipoDeImovel;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cliente getClienteProprietario() {
        return clienteProprietario;
    }

    public void setClienteProprietario(Cliente clienteProprietario) {
        this.clienteProprietario = clienteProprietario;
    }

    public Date getDataTerminoConstrucao() {
        return dataTerminoConstrucao;
    }

    public void setDataTerminoConstrucao(Date dataTerminoConstrucao) {
        this.dataTerminoConstrucao = dataTerminoConstrucao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public List<Caracteristica> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public void setListaCaracteristicas(List<Caracteristica> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }

    public List<Reforma> getListaReformas() {
        return listaReformas;
    }

    public void setListaReformas(List<Reforma> listaReformas) {
        this.listaReformas = listaReformas;
    }
    
    public void addReforma(Reforma r){
        if(!listaReformas.contains(r)){
            listaReformas.add(r);
        }
    }
    
    public void removeReforma(Reforma r){
        if(listaReformas.contains(r)){
            listaReformas.remove(r);
        }
    }
    
    public void addFoto(Foto f){
        if(!listaFotos.contains(f)){
            listaFotos.add(f);
        }
    }
    
    public void removeFoto(Foto f){
        if(listaFotos.contains(f)){
            listaFotos.add(f);
        }
    }
    
    public void addCaracteristica(Caracteristica c){
        if(!listaCaracteristicas.contains(c)){
            listaCaracteristicas.add(c);
        }
    }
    
    public void removeCaracteristica(Caracteristica c){
        if(listaCaracteristicas.contains(c)){
            listaCaracteristicas.remove(c);
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Float.floatToIntBits(this.dimensoes);
        hash = 79 * hash + Objects.hashCode(this.dataInicioConstrucao);
        hash = 79 * hash + Objects.hashCode(this.tipoDeImovel);
        hash = 79 * hash + Objects.hashCode(this.cidade);
        hash = 79 * hash + Objects.hashCode(this.clienteProprietario);
        hash = 79 * hash + Objects.hashCode(this.dataTerminoConstrucao);
        hash = 79 * hash + Objects.hashCode(this.latitude);
        hash = 79 * hash + Objects.hashCode(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Imovel other = (Imovel) obj;
        if (Float.floatToIntBits(this.dimensoes) != Float.floatToIntBits(other.dimensoes)) {
            return false;
        }
        if (!Objects.equals(this.dataInicioConstrucao, other.dataInicioConstrucao)) {
            return false;
        }
        if (!Objects.equals(this.tipoDeImovel, other.tipoDeImovel)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.clienteProprietario, other.clienteProprietario)) {
            return false;
        }
        if (!Objects.equals(this.dataTerminoConstrucao, other.dataTerminoConstrucao)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
    
}
