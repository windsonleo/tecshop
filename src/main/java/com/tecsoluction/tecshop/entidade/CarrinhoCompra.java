package com.tecsoluction.tecshop.entidade;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tecsoluction.tecshop.framework.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "CARRINHOCOMPRA")
public class CarrinhoCompra extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "itens_carrinho", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "qtd")
    @MapKeyColumn(name = "idit")
    private Map<Item, String> itens = new HashMap<Item, String>();
    
    private BigDecimal total;
    
    @OneToOne(fetch= FetchType.EAGER,mappedBy="carrinho")
    @JoinColumn(name="ordem_id")
    private Ordem ordem;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    
    
    public CarrinhoCompra() {
        // TODO Auto-generated constructor stub
    }
    
    
    public CarrinhoCompra(Cliente cliente) {
    	this.cliente = cliente;

    }

    
    
    
    public void addNovoItem(Item item , String qtd){
    	
    	if(this.itens == null) {
    		this.itens = new HashMap<Item, String>();
    		
    		
    	}
    	
    	this.itens.put(item, qtd);
    	
    }
    
    public void removerItem(Item itemRemove){
    	
//    	for(Iterator i = itens.iterator();i.hasNext();){
//    		
//    		Item iten = (Item) i.next();
//    		
//    		if(iten.getNome() ==  itemRemove.getNome()){
//    			
//    			i.remove();
//    		}
//    	}
    	
    	this.getItens().remove(itemRemove);
    	
    	
    }
    		
    	public BigDecimal CalcularTotal() {

        	BigDecimal totalpedido = new BigDecimal("0.00").setScale(2, RoundingMode.UP);
        	BigDecimal totalpedidoaux = new BigDecimal("0.00").setScale(2, RoundingMode.UP);


            for (Item key : getItens().keySet()) {
            	
            	//QTD ITEM
            	String total = getItens().get(key);
            	
            	totalpedidoaux = new  BigDecimal(total);
            	
            	BigDecimal totalped = new BigDecimal(key.getPrecoUnitario().toString());
            	
            	totalped = totalped.multiply(totalpedidoaux);
            	

            	totalpedido = totalpedido.add(totalped);
            }
            
            this.total = totalpedido;



            return totalpedido;
        }
    
	@Override
	public String toString() {
		return "CarrinhoCompra [id=" + id + ", itens=" + itens + ", total=" + total + "]";
	}
    
    

}
