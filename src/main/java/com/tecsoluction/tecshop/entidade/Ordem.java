package com.tecsoluction.tecshop.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.tecsoluction.tecshop.framework.BaseEntity;
import com.tecsoluction.tecshop.util.StatusPedido;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "ORDEM")
public class Ordem extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern = "dd/MM/yyyy")
	    private Date data;


	    @Column(name = "total")
	    private BigDecimal  total ;


	    //aberto,pendente,fechado,cancelado,ENTREGUE
	    @Enumerated(EnumType.STRING)
	    private StatusPedido status;

	    @ManyToOne(fetch=FetchType.EAGER)
	    @JoinColumn(name="cliente_id")
	    private Cliente cliente;
	    
	    @OneToOne(fetch=FetchType.EAGER)
	    private CarrinhoCompra carrinho;
	    
	    private boolean ispago = false;
	    
	public Ordem() {
		// TODO Auto-generated constructor stub
	}    
	    
	
	public Ordem(CarrinhoCompra carrinho) {

	this.carrinho = carrinho;
	
	}   
	
}
