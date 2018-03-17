package com.tecsoluction.tecshop.controller;


import java.util.List;
import java.util.UUID;

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

import com.tecsoluction.tecshop.entidade.Categoria;
import com.tecsoluction.tecshop.entidade.Fornecedor;
import com.tecsoluction.tecshop.entidade.Produto;
import com.tecsoluction.tecshop.entidade.Usuario;
import com.tecsoluction.tecshop.framework.AbstractController;
import com.tecsoluction.tecshop.framework.AbstractEditor;
import com.tecsoluction.tecshop.servico.CategoriaServicoImpl;
import com.tecsoluction.tecshop.servico.UsuarioServicoImpl;


@Controller
@RequestMapping("categoria/")
public class CategoriaController extends AbstractController<Categoria> {

	
	private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

	private final CategoriaServicoImpl categoriaService;

	private final UsuarioServicoImpl userservice;

	
	
	@Autowired
	public CategoriaController(CategoriaServicoImpl dao, UsuarioServicoImpl daousu) {
		super("categoria");
		this.categoriaService = dao;
		this.userservice = daousu;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Categoria.class, new AbstractEditor<Categoria>(this.categoriaService) {

		});
	}
	
    @ModelAttribute
    public void addAttributes(Model model) {


        Usuario usuario = new Usuario();
        usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        usuario = userservice.findByUsername(usuario.getUsername());

        model.addAttribute("usuarioAtt", usuario);
        model.addAttribute("categoria", new Categoria());

    }
    
    
   
    

	@Override
	protected CategoriaServicoImpl getservice() {

		return categoriaService;
	}
    
    

}
