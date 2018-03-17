package com.tecsoluction.tecshop.entidade;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tecsoluction.tecshop.framework.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "CATEGORIA")
public class Categoria extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 1L;


	@Column(name = "NOME", nullable = false)
	@NotNull(message="nome não pode ser nulo")
    private String nome;

    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Categoria.class, optional = true)
	@JoinColumn(name = "CAT_PAI", nullable = true)
    private Categoria catpai;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;



    public Categoria() {
        // TODO Auto-generated constructor stub
    }


    
    @Override
    public String toString() {
        return nome;
    }

}
