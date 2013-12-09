/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.domainModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Anderson
 */
@Entity
@Table(name = "vendaimovel")
public class VendaImovel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVenda;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataRegistro;
    
    private String observacoes;
    
    private float percentualComissao;
    
    @ManyToOne
    FormaDePagamento formaDePagamento;
    
    @ManyToOne
    Cliente clienteVendedor;
    @ManyToOne
    Cliente clienteComprador;
    
    @ManyToOne
    Funcionario funcionario;
    
    @ManyToOne
    Imovel imovel;
    
    private boolean ativo;

    public VendaImovel() {
        this.dataRegistro = new Date();
        this.dataVenda = null;
        this.formaDePagamento = null;
        this.clienteVendedor = null;
        this.clienteComprador = null;
        this.funcionario = null;
        this.imovel = null;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
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

    public Cliente getClienteVendedor() {
        return clienteVendedor;
    }

    public void setClienteVendedor(Cliente clienteVendedor) {
        this.clienteVendedor = clienteVendedor;
    }

    public Cliente getClienteComprador() {
        return clienteComprador;
    }

    public void setClienteComprador(Cliente clienteComprador) {
        this.clienteComprador = clienteComprador;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.dataVenda);
        hash = 19 * hash + Objects.hashCode(this.clienteVendedor);
        hash = 19 * hash + Objects.hashCode(this.clienteComprador);
        hash = 19 * hash + Objects.hashCode(this.funcionario);
        hash = 19 * hash + Objects.hashCode(this.imovel);
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
        final VendaImovel other = (VendaImovel) obj;
        if (!Objects.equals(this.dataVenda, other.dataVenda)) {
            return false;
        }
        if (!Objects.equals(this.dataRegistro, other.dataRegistro)) {
            return false;
        }
        if (!Objects.equals(this.clienteVendedor, other.clienteVendedor)) {
            return false;
        }
        if (!Objects.equals(this.clienteComprador, other.clienteComprador)) {
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
        return "VendaImovel{" + "id=" + id + ", dataVenda=" + dataVenda + ", dataRegistro=" + dataRegistro + ", observacoes=" + observacoes + ", percentualComissao=" + percentualComissao + ", formaDePagamento=" + formaDePagamento + ", clienteVendedor=" + clienteVendedor + ", clienteComprador=" + clienteComprador + ", funcionario=" + funcionario + ", imovel=" + imovel + '}';
    }
    
    
}
