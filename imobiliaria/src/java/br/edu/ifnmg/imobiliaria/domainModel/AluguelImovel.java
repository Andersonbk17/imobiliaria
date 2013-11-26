/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.domainModel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Anderson
 */
@Entity
public class AluguelImovel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataTermino;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataRegistro;
    
    private String observacoes;
    
    private float percentualComissao;
    
    @ManyToOne
    FormaDePagamento formaDePagamento;
    
   
    @ManyToOne
    Cliente cliente;
    
    @ManyToOne
    Funcionario funcionario;
    
    @ManyToOne
    Imovel imovel;

    public AluguelImovel() {
        this.dataRegistro.setTime(new Date());
        this.formaDePagamento = null;
        this.imovel = null;
        this.cliente = null;
        this.funcionario = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Calendar dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Calendar getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Calendar dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public float getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(float percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.dataInicio);
        hash = 97 * hash + Objects.hashCode(this.dataTermino);
        hash = 97 * hash + Objects.hashCode(this.dataRegistro);
        hash = 97 * hash + Objects.hashCode(this.cliente);
        hash = 97 * hash + Objects.hashCode(this.funcionario);
        hash = 97 * hash + Objects.hashCode(this.imovel);
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
        final AluguelImovel other = (AluguelImovel) obj;
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataTermino, other.dataTermino)) {
            return false;
        }
        if (!Objects.equals(this.dataRegistro, other.dataRegistro)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        if (!Objects.equals(this.imovel, other.imovel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AluguelImovel{" + "id=" + id + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", dataRegistro=" + dataRegistro + ", observacoes=" + observacoes + ", percentualComissao=" + percentualComissao + ", formaDePagamento=" + formaDePagamento + ", cliente=" + cliente + ", funcionario=" + funcionario + ", imovel=" + imovel + '}';
    }
    
}
