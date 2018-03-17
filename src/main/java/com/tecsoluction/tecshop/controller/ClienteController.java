package com.tecsoluction.tecshop.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tecsoluction.tecshop.entidade.Categoria;
import com.tecsoluction.tecshop.entidade.Cliente;
import com.tecsoluction.tecshop.entidade.Endereco;
import com.tecsoluction.tecshop.entidade.Usuario;
import com.tecsoluction.tecshop.framework.AbstractController;
import com.tecsoluction.tecshop.framework.AbstractEditor;
import com.tecsoluction.tecshop.servico.ClienteServicoImpl;
import com.tecsoluction.tecshop.servico.EnderecoServicoImpl;
import com.tecsoluction.tecshop.servico.UsuarioServicoImpl;
import com.tecsoluction.tecshop.util.Genero;


@Controller
@RequestMapping("cliente/")
public class ClienteController extends AbstractController<Cliente> {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	
	private final ClienteServicoImpl clienteService;

    private final EnderecoServicoImpl enderecoService;

    private final UsuarioServicoImpl userservice;
    
    
    private Cliente cliente;
	
    private Endereco endereco;
    
    
	
    @Autowired
    public ClienteController(ClienteServicoImpl cliimpl,UsuarioServicoImpl usuimpl,EnderecoServicoImpl endimpl) {
		super("cliente");
		this.clienteService = cliimpl;
		this.userservice = usuimpl;
		this.enderecoService=endimpl;
		
	}


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Endereco.class, new AbstractEditor<Endereco>(this.enderecoService) {
        });
    }
	
	
    @ModelAttribute
    public void addAttributes(Model model) {


        Usuario usuario = new Usuario();
        usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        usuario = userservice.findByUsername(usuario.getUsername());

        
        Genero genero[] = Genero.values();
        
        
//        if(cliente == null) {
//        	
//        	cliente = new Cliente();
//        	
//        }
        
        model.addAttribute("usuarioAtt", usuario);
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("genero", genero);
        model.addAttribute("endereco", new Endereco());

        

    }
    
    
    // verificar tmanho do arquivo e se o arquivo ja existe
    @RequestMapping(value = "salvarfotocliente", method = RequestMethod.POST)
    public ModelAndView SalvarFotoUsuario(@RequestParam ("file") MultipartFile file, HttpSession session, HttpServletRequest request,
                             Model model) {

        String mensagem = "Sucesso ao salvar foto";
        String erros = "Falha ao salvar foto";
        
        ModelAndView cadastro = new ModelAndView("/private/cliente/cadastro/cadastrocliente");

        String path = session.getServletContext().getRealPath("/");

        String d = request.getContextPath();

        String pathh = "/resources/static/img/cliente";
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
    
    
    @RequestMapping(value = "addEndereco", method = RequestMethod.GET)
    public ModelAndView addEnderecoClienteForm(HttpServletRequest request, Model model) {

        UUID id = UUID.fromString(request.getParameter("id"));
 
        cliente = getservice().findOne(id);
        
//        cliente.setEndereco(endereco);


        ModelAndView cadastroendereco = new ModelAndView("/private/endereco/cadastro/cadastroendereco");


        cadastroendereco.addObject("cliente", cliente);
        cadastroendereco.addObject("endereco", new Endereco());


        return cadastroendereco;
    }


    @RequestMapping(value = "addEndereco", method = RequestMethod.POST)
    public ModelAndView addEnderecoCliente(HttpServletRequest request, Model model) {


	  	ModelAndView movimentacaocliente= new ModelAndView("/private/cliente/movimentacao/movimentacaocliente");

        UUID id = UUID.fromString(request.getParameter("id"));

        Endereco endereco = new Endereco();

        endereco.setLogradouro(request.getParameter("logradouro"));
        endereco.setNumero(request.getParameter("numero"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setUf(request.getParameter("uf"));
        endereco.setCep(request.getParameter("cep"));
        endereco.setPontoreferencia(request.getParameter("pontoreferencia"));
        endereco.setComplemento(request.getParameter("complemento"));
        endereco.setAtivo(true);

//		String datanascimento = request.getParameter("datanascimento");

//		SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
//		
//		Date data = null;
//		
//		try {
//			data = df.parse(datanascimento);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        endereco = enderecoService.save(endereco);


        cliente = getservice().findOne(id);


//			cliente.setNome(request.getParameter("nome"));
//			cliente.setTelefone(request.getParameter("telefone"));
////			cliente.setDatanascimento(data);
//			cliente.setEmail(request.getParameter("email"));
//			cliente.setFoto(request.getParameter("foto"));
//			cliente.setGenero(request.getParameter("genero"));
//			cliente.setativo(true);

        cliente.setEndereco(endereco);


        getservice().edit(cliente);
       
//        endereco.setCliente(cliente);
        
//        enderecoService.edit(endereco);

//  	ModelAndView cadastrocliente= new ModelAndView("cadastrocliente");
//		
//		
//  	cadastrocliente.addObject("cliente",cliente);


        return movimentacaocliente;
        
//        return new ModelAndView("forward:/cliente/movimentacao");
    }

    

	@Override
	protected ClienteServicoImpl getservice() {

		return clienteService;
	}
    
    

}
