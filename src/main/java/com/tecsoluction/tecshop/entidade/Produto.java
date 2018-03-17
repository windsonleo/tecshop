package com.tecsoluction.tecshop.entidade;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tecsoluction.tecshop.framework.BaseEntity;
import com.tecsoluction.tecshop.util.UnidadeMedida;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "PRODUTO")
public class Produto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(name = "CODEBAR")
    private String codebar;
    
    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "UN_MEDIDA")
    @Enumerated(EnumType.STRING)
    private UnidadeMedida un_medida;

    @Column(name = "PRECO_CUSTO")
    private BigDecimal precoCusto;

    @Column(name = "PRECO_VENDA")
    private BigDecimal precoVenda;
    
    private String image;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Fornecedor fornecedor;


    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    

    public Produto() {
        // TODO Auto-generated constructor stub
        // items = new ArrayList<Item>();
    }

   

    @Override
    public String toString() {
        return descricao;
    }

}
