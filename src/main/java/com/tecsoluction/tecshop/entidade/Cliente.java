package com.tecsoluction.tecshop.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.tecsoluction.tecshop.framework.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CLIENTE")
public class Cliente extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3173051655074157450L;


	@Column(name = "nome")
    private String nome;

    
    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento")
    private Date datanascimento;

    @Column(name = "foto")
    private String foto;
    
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "rg")
    private String rg;
    
    @Column(name = "genero")
    private String genero;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Endereco endereco;
    
    @OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
    private Set<Ordem> ordens;


//    @JsonIgnore
//    @OneToMany(mappedBy = "cliente",fetch=FetchType.EAGER)
//    private Set<PedidoVenda> listaPedidoVenda;
//    
//    
//    @OneToMany(mappedBy = "cliente",fetch=FetchType.EAGER)
//    private Set<Reserva> reservas;


//    @JsonIgnore
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany
//    private List<DevolucaoVenda> listaDevolucaoVenda;
//

    public Cliente() {

    }

    public Cliente(Endereco endereco) {

        this.endereco = endereco;
    }


    
    @Override
    public String toString() {
        return nome.toUpperCase();
    }

    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return super.hashCode();
    }
}