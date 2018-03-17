package com.tecsoluction.tecshop.controller;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tecsoluction.tecshop.entidade.CarrinhoCompra;
import com.tecsoluction.tecshop.entidade.Item;
import com.tecsoluction.tecshop.entidade.Produto;
import com.tecsoluction.tecshop.framework.AbstractEditor;
import com.tecsoluction.tecshop.servico.CarrinhoCompraServicoImpl;
import com.tecsoluction.tecshop.servico.ProdutoServicoImpl;
	

@Controller
@RequestMapping(value = "/carrinhocompra")
public class CarrinhoCompraController {
	
	private static final Logger logger = LoggerFactory.getLogger(CarrinhoCompraController.class);

	@Autowired
    private  ProdutoServicoImpl produtoservice;
    
	@Autowired
    private  CarrinhoCompraServicoImpl carrinhoservice;
	
	
    private List<Produto> produtoList;
    
   
    private List<Item> itemList;


    private CarrinhoCompra carrinhocompra;
 
    boolean ExisteCarrinho = false;

	private int contador;
    
    
    //cosntrutor
    public CarrinhoCompraController() {
    	
    	 if(this.carrinhocompra == null){
    		 
    		 this.carrinhocompra = new CarrinhoCompra();
    		 this.contador = 0;
    	 }
    
    
    }
	

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Produto.class, new AbstractEditor<Produto>(produtoservice) {
        });
        

    }



    @ModelAttribute
    public void addAttributes(Model model) {

          model.addAttribute("carrinhocompra", carrinhocompra);

    }
    
    
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView ExibirCarrinhoForm() {

    	
    	
        ModelAndView exibircarrinho = new ModelAndView("/public/checkout");
        
//        itemList = itemservice.getItemDao().todos();
        
        
        

  		
        	exibircarrinho.addObject("itemList",carrinhocompra.getItens());
        	exibircarrinho.addObject("carrinhocompra",carrinhocompra);
        	
        	

        return exibircarrinho;
    }
    

    @RequestMapping(value = "/addproduto", method = RequestMethod.GET)
    public ModelAndView AddItemCarrinho(Model model, HttpServletRequest request) {
       
    	    	
    	UUID idf = UUID.fromString(request.getParameter("id"));//    
    	    	
    	Produto produ;    	
    	produ=produtoservice.findOne(idf);
    	
    	logger.info("produto", produ.toString());
    	
    	Item item = new Item(produ);
//    	int quant = 5;
//    	item.setId(this.contador);
//    	item.setNome(produ.getNome());
//    	item.setQuantidade(quant);
//    	item.setPrecounit(produ.getPrecoVenda());
//    	item.setTotal(produ.getPrecoVenda() * quant);
    	
    	
    	this.carrinhocompra.addNovoItem(item , "1");
    	
    	this.contador = carrinhocompra.getItens().size();
    	
    	carrinhocompra.CalcularTotal();
    	
//    	itemservice.getItemDao().atualizar(item);

//    	carrinhoservice.getCarrinhoCompraDao().atualizar(carrinhocompra);
    	carrinhoservice.save(carrinhocompra);
    	
    	

        return new ModelAndView("forward:/");
    }
//
//    
    @RequestMapping(value = "/excluir", method = RequestMethod.GET)
    public ModelAndView ExcluirItemCarrinho(HttpServletRequest request) {

    	
    	 UUID idf = UUID.fromString(request.getParameter("id"));
    	      	
//    	Item item = carrinhocompra.getItens().get(idf);
   
//		this.carrinhocompra.removerItem(item);
		
    	this.contador = carrinhocompra.getItens().size();

		
		this.carrinhocompra.CalcularTotal();
		
//		logger.info("item removido",item.getNome());


        return ExibirCarrinhoForm();
    }
    
    
    
//    public boolean VerificarCarrinho(){
//		
//
//    	if(this.carrinhocompra == null){
//    	
//    		this.ExisteCarrinho = false;
//    	
//    	return ExisteCarrinho;
//    	
//    	}	else   	
//    		
//    		this.ExisteCarrinho = true;
//    		
//    	
//			return ExisteCarrinho;
//    	
//
//    }
    
    @RequestMapping(value = "/esvaziarcarrinho", method = RequestMethod.GET)
    public ModelAndView EsvaziarCarrinho(Locale locale, Model model) {
       
    	logger.info("Welcome EsvaziarCarrinho ! The client locale is {}.", locale);

        ModelAndView home = new ModelAndView("/public/checkout");
        
        carrinhocompra.getItens().clear();
        carrinhocompra.CalcularTotal();
        carrinhoservice.edit(carrinhocompra);
        
        home.addObject("carrinhocompra",carrinhocompra);

        return new ModelAndView("forward:/");
    }
}
