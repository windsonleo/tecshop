package com.tecsoluction.tecshop.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tecsoluction.tecshop.entidade.Categoria;
import com.tecsoluction.tecshop.entidade.Cliente;
import com.tecsoluction.tecshop.entidade.Fornecedor;
import com.tecsoluction.tecshop.entidade.Produto;
import com.tecsoluction.tecshop.entidade.Usuario;
import com.tecsoluction.tecshop.framework.AbstractController;
import com.tecsoluction.tecshop.framework.AbstractEditor;
import com.tecsoluction.tecshop.servico.CategoriaServicoImpl;
import com.tecsoluction.tecshop.servico.FornecedorServicoImpl;
import com.tecsoluction.tecshop.servico.ProdutoServicoImpl;
import com.tecsoluction.tecshop.servico.UsuarioServicoImpl;
import com.tecsoluction.tecshop.util.Genero;
import com.tecsoluction.tecshop.util.UnidadeMedida;


@Controller
@RequestMapping("produto/")
public class ProdutoController extends AbstractController<Produto> {
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	 private final UsuarioServicoImpl userservice;

	 private final ProdutoServicoImpl produtoService;

	 private final FornecedorServicoImpl fornecedorService;

	 private final CategoriaServicoImpl categoriaService;
	 
	 private List<Categoria> categorias;
	 
	 private List<Fornecedor> fornecedores;

	
    @Autowired
    public ProdutoController(ProdutoServicoImpl dao, CategoriaServicoImpl categoriaDao,
                             FornecedorServicoImpl fornecedorDao, UsuarioServicoImpl usudao) {
        super("produto");
        this.produtoService = dao;
        this.categoriaService = categoriaDao;
        this.fornecedorService = fornecedorDao;
        this.userservice = usudao;
    }


	    @InitBinder
	    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

	        binder.registerCustomEditor(Categoria.class, new AbstractEditor<Categoria>(this.categoriaService) {
	        });

	        binder.registerCustomEditor(Fornecedor.class, new AbstractEditor<Fornecedor>(this.fornecedorService) {
	        });

	    }

	    @ModelAttribute
	    public void addAttributes(Model model) {
	    	
	    	categorias = categoriaService.findAll();
	    	
	    	fornecedores = fornecedorService.findAll();

	        Usuario usuario = new Usuario();
	        usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	        usuario = userservice.findByUsername(usuario.getUsername());

	        
	        UnidadeMedida medida[] = UnidadeMedida.values();
	        
	        model.addAttribute("usuarioAtt", usuario);
	        model.addAttribute("produto", new Produto());
	        model.addAttribute("medida", medida);
	        model.addAttribute("categorias", categorias);
	        model.addAttribute("fornecedores", fornecedores);
 

	    }
	    
	    
//	    // verificar tmanho do arquivo e se o arquivo ja existe
//	    @RequestMapping(value = "salvarfotoproduto", method = RequestMethod.POST)
//	    public ModelAndView SalvarFotoProduto(@RequestParam ("file") MultipartFile file, HttpSession session, HttpServletRequest request,
//	                             Model model) {
//
//	        String mensagem = "Sucesso ao salvar foto";
//	        
//	        String erros = "Falha ao salvar foto";
//	        
//	        ModelAndView cadastro = new ModelAndView("/private/produto/cadastro/cadastroproduto");
//
//	        String path = session.getServletContext().getRealPath("/");
//
//	        String d = request.getContextPath();
//
//	        String pathh = "/resources/static/img/produto";
//	        // string pathh = file.get
//
////	        String filename = file.getOriginalFilename();
////
////	        System.out.println("Caminho" + path + " " + filename);
////
////	        System.out.println("request end" + d + pathh + "/" + filename);
////	        
////	        System.out.println("request D" + d);
//	        
//	        String filename = file.getOriginalFilename();
//
//	        System.out.println("Caminho path = "  + path );
//
//	        System.out.println("request pathh" + pathh);
//	        
//	        System.out.println("request D" + d);
//
//	        try {
//
//	            byte barr[] = file.getBytes();
//
//	            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + pathh + "/" + filename));
//	            bout.write(barr);
//	            bout.flush();
//	            bout.close();
//
//	            model.addAttribute("mensagem", mensagem);
//	            model.addAttribute("filename", filename);
//	            model.addAttribute("acao", "add");
//
//	        } catch (Exception e) {
//
//	            System.out.println(e);
//
//	            model.addAttribute("erros", erros + e);
//	            model.addAttribute("acao", "add");
//
//	        }
//
//	        return cadastro;
//
//	    }
	    
	    
	    
	    @RequestMapping(value = "produtoporcategoria/", method = RequestMethod.GET)
	    public ModelAndView ProdutosPorCategoria ( HttpServletRequest request){
	    	
	    	UUID idf = UUID.fromString(request.getParameter("id"));
	    	
	    	Categoria categoria = categoriaService.findOne(idf);
	    	
	        ModelAndView cadastro = new ModelAndView("/public/produtoporcategoria");
	        
	        List<Produto> produtosporcategoria = produtoService.getAllProdutoPorCategoria(categoria);
	        
	        cadastro.addObject("produtos", produtosporcategoria);
	        cadastro.addObject("categoria", categoria);

	        
	        
	        
	    	
	    	return cadastro;
	    }
	    
	    
	    @RequestMapping(value = "detalhes/", method = RequestMethod.GET)
	    public ModelAndView ProdutoDetalhes ( HttpServletRequest request){
	    	
	    	UUID idf = UUID.fromString(request.getParameter("id"));
//	    	
	    	Produto produto = getservice().findOne(idf);
	    	
	        ModelAndView detalhes = new ModelAndView("/public/detalhes");
	        	        
	        detalhes.addObject("produto", produto);
	        
	    	return detalhes;
	    }

	    
	    // verificar tmanho do arquivo e se o arquivo ja existe
	    @RequestMapping(value = "salvarfotoproduto", method = RequestMethod.POST)
	    public ModelAndView SalvarFotoProduto2(@RequestParam ("file") MultipartFile file, HttpSession session, HttpServletRequest request,
	                             Model model) {

	        String mensagem = "Sucesso ao salvar foto";
	        
	        String erros = "Falha ao salvar foto";
	        
//	        ModelAndView cadastro = new ModelAndView("/private/produto/cadastro/cadastroproduto");

	        String path = session.getServletContext().getRealPath("/WEB-INF/classes/static/img/produto");
	        
	        String filename = file.getOriginalFilename();
	        
	        String caminho = path + "\\" + filename;
	        


	        System.out.println(" path = "  + path );

	        System.out.println(" caminho" + caminho);
//	        
//	        System.out.println("request D" + d);

	        try {

	            byte barr[] = file.getBytes();

	            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(caminho));
	            bout.write(barr);
	            bout.flush();
	            bout.close();

	            model.addAttribute("mensagem", mensagem);
	            model.addAttribute("filename", filename);
	            model.addAttribute("acao", "add");

	        } catch (Exception e) {

	            System.out.println(e);

	            model.addAttribute("erros", erros + e);
	            model.addAttribute("acao", "add");

	        }

	        Produto produtocomfoto = new Produto();
	        produtocomfoto.setImage(filename);
	        
	       return new ModelAndView("redirect:/produto/cadastro").addObject("produto", produtocomfoto);

	    }


	@Override
	protected ProdutoServicoImpl getservice() {
		// TODO Auto-generated method stub
		return produtoService;
	}
    
    

}
