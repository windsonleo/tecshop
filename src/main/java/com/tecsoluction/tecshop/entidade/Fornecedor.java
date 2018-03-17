package com.tecsoluction.tecshop.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.tecsoluction.tecshop.framework.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "FORNECEDOR")
public class Fornecedor  extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;



    @Column(name = "NOME_FANTASIA")
    private String nomefantasia;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "INSCRICAOESTADUAL")
    private String inscricaoestadual;
    
    
    private String telefone;
    
    private String endereco;
    
    @OneToMany(mappedBy = "fornecedor")
    private List<Produto> produtos;

    
    
    public Fornecedor() {
    //    produtos = new ArrayList<>();
    }

 

    @Override
    public String toString() {
        return nomefantasia;
    }

}
