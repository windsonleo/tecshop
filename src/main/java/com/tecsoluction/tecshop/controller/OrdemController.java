package com.tecsoluction.tecshop.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tecsoluction.tecshop.entidade.CarrinhoCompra;
import com.tecsoluction.tecshop.entidade.Categoria;
import com.tecsoluction.tecshop.entidade.Cliente;
import com.tecsoluction.tecshop.entidade.Ordem;
import com.tecsoluction.tecshop.entidade.Usuario;
import com.tecsoluction.tecshop.framework.AbstractController;
import com.tecsoluction.tecshop.servico.OrdemServicoImpl;
import com.tecsoluction.tecshop.servico.UsuarioServicoImpl;


@Controller
@RequestMapping("ordem/")
public class OrdemController extends AbstractController<Ordem> {

	
	private static final Logger logger = LoggerFactory.getLogger(OrdemController.class);

	private final OrdemServicoImpl ordemService;

	private final UsuarioServicoImpl userservice;

	
	
	@Autowired
	public OrdemController(OrdemServicoImpl dao, UsuarioServicoImpl daousu) {
		super("ordem");
		this.ordemService = dao;
		this.userservice = daousu;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
//		binder.registerCustomEditor(Categoria.class, new AbstractEditor<Categoria>(this.categoriaService) {
//
//		});
	}
	
    @ModelAttribute
    public void addAttributes(Model model) {

    	
    	Ordem ordem = new Ordem();
    	
    	Cliente cliente = new Cliente();
    	
    	CarrinhoCompra carrinho = new CarrinhoCompra();
    	
    	ordem.setCliente(cliente);
    	ordem.setCarrinho(carrinho);
    	

        Usuario usuario = new Usuario();
        usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        usuario = userservice.findByUsername(usuario.getUsername());

        model.addAttribute("usuarioAtt", usuario);
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("ordem", ordem);


    }
    
    
    @RequestMapping(value = "/gerarpedido", method = RequestMethod.GET)
    public ModelAndView GerarPedido(Locale locale, Model model) {
       
    	logger.info("Welcome gerarpedido /! The client locale is {}.", locale);

        ModelAndView home = new ModelAndView("/public/ordem");


        return home;
    }
    

	@Override
	protected OrdemServicoImpl getservice() {

		return ordemService;
	}
    
    

}
