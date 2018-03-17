package com.tecsoluction.tecshop.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tecsoluction.tecshop.entidade.Categoria;
import com.tecsoluction.tecshop.entidade.Cliente;
import com.tecsoluction.tecshop.entidade.Empresa;
import com.tecsoluction.tecshop.entidade.Fornecedor;
import com.tecsoluction.tecshop.entidade.Usuario;
import com.tecsoluction.tecshop.framework.AbstractController;
import com.tecsoluction.tecshop.framework.AbstractEntityService;
import com.tecsoluction.tecshop.servico.EmpresaServicoImpl;
import com.tecsoluction.tecshop.servico.UsuarioServicoImpl;
import com.tecsoluction.tecshop.util.Genero;


@Controller
@RequestMapping("empresa/")
public class EmpresaController extends AbstractController<Empresa> {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
	
	private final EmpresaServicoImpl empresaService;

	private final UsuarioServicoImpl userservice;

	
	@Autowired
	public EmpresaController(EmpresaServicoImpl dao, UsuarioServicoImpl daousu) {
	    super("empresa");
	    this.empresaService = dao;
	    this.userservice = daousu;
	}
	
	
    @ModelAttribute
    public void addAttributes(Model model) {


        Usuario usuario = new Usuario();
        usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        usuario = userservice.findByUsername(usuario.getUsername());
        
        model.addAttribute("usuarioAtt", usuario);
        model.addAttribute("empresa", new Empresa());


    }
	
	
	
	// verificar tmanho do arquivo e se o arquivo ja existe

	@RequestMapping(value = "salvarfotoempresa", method = RequestMethod.POST)
    public ModelAndView SalvarFotoUsuario(@RequestParam ("file") MultipartFile file, HttpSession session, HttpServletRequest request,
                             Model model) {

        String mensagem = "Sucesso ao salvar foto";
        String erros = "Falha ao salvar foto";
        
        ModelAndView cadastro = new ModelAndView("/private/empresa/cadastro/cadastroempresa");

        String path = session.getServletContext().getRealPath("/");

        String d = request.getContextPath();

        String pathh = "/resources/static/img/empresa";
        // string pathh = file.get

        String filename = file.getOriginalFilename();

        System.out.println("Caminho" + path + " " + filename);

        System.out.println("request end" + d + pathh + "/" + filename);

        try {

            byte barr[] = file.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + pathh + "/" + filename));
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

        return cadastro;

    }


	@Override
	protected EmpresaServicoImpl getservice() {
		// TODO Auto-generated method stub
		return empresaService;
	}
    
    

}
