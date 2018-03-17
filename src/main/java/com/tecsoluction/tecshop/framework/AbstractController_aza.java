package com.tecsoluction.tecshop.framework;
//package com.tecsoluction.tecshop.framework;
//
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.List;
//import java.util.UUID;
//
//
//public abstract class AbstractController<Entity> {
//
//    private final String entityAlias;
//
//    public AbstractController(String entityAlias) {
//        this.entityAlias = entityAlias;
//    }
//
//    protected abstract AbstractEntityService<Entity> getservice();
//    
//    
//    @GetMapping(value = "cadastro")
//    public ModelAndView cadastrarEntity() {
//
//        ModelAndView cadastro = new ModelAndView("/private/" + entityAlias+ "/cadastro/" + "cadastro"+ entityAlias);
//
//        List<Entity> entityList = getservice().findAll();
//
//        cadastro.addObject("acao", "add");
//
//
//        return cadastro;
//
//        // return new ModelAndView("cadastro" + entityAlias);
//    }
//
//    @Transactional
//    @PostMapping(value = "add")
//    public ModelAndView AdicionarEntity(@ModelAttribute @Valid Entity entity, BindingResult result,
//                                        RedirectAttributes attributes) {
//
//        ModelAndView cadastroEntity = new ModelAndView("/private/" + entityAlias + "/cadastro/" +"cadastro"+ entityAlias);
//
//        if (result.hasErrors()) {
//            System.out.println("erro ao add Entidade: " + entityAlias + " erro: " + result.getObjectName());
//            System.out.println("erro ap add Entidade: " + entityAlias + " fields erro: " + result.getFieldError());
//            System.out.println(
//                    "erro ao add Entidade: " + entityAlias + " outros erros global: " + result.getGlobalError());
//            System.out.println(
//                    "erro ao add Entidade: " + entityAlias + " outros erros nestedPatch: " + result.getNestedPath());
//            attributes.addFlashAttribute("erros", "Erro ao Salvar." + result.getFieldError());
//        } else {
//            getservice().save(entity);
//            System.out.println("add: " + entityAlias);
//
//            attributes.addFlashAttribute("mensagem", "Sucesso ao Salvar.");
//            attributes.addFlashAttribute("entity", entity.toString());
//        }
//
////        cadastroEntity.addObject("entity", entity);
////        cadastroEntity.addObject("acao", "add");
//
////        return new ModelAndView("redirect:/" + entityAlias + "/" + "cadastro"); // cadastroEntity;
//        
//        return cadastroEntity;
//    }
//
//
//    @GetMapping(value = "movimentacao")
//    public ModelAndView movimentacaoEntity() {
//
//        ModelAndView movimentacao = new ModelAndView("/private/"+ entityAlias+"/movimentacao/movimentacao" + entityAlias);
//        List<Entity> entityList = getservice().findAll();
//        movimentacao.addObject(entityAlias + "List", entityList);
//       
//        return movimentacao;
//    }
//
//      
//    
//
//    @Transactional
//    @GetMapping(value = "editar")
//    public ModelAndView editarEntityForm(HttpServletRequest request) {
//
//        Entity entitys;
//
//        UUID idf = UUID.fromString(request.getParameter("id"));
//        entitys = getservice().findOne(idf);
//
//        ModelAndView cadastroEntity = new ModelAndView("/private/"+ entityAlias +"/cadastro/" + "cadastro" + entityAlias);
//
//        cadastroEntity.addObject("acao", "edicao");
//        cadastroEntity.addObject(entityAlias, entitys);
//
//        return cadastroEntity;
//    }
//
//
//    @Transactional
//    @PostMapping(value = "edicao")
//    public ModelAndView editarEntity(@ModelAttribute @Valid Entity entity, BindingResult result,
//                                     RedirectAttributes attributes) {
//
//        String mensagem = "Sucesso ao editar! ";
//        String erros = "Erros ao Editar! ";
//
//        ModelAndView cadastroEntity = new ModelAndView("/private/"+ entityAlias +"/cadastro/" + "cadastro" + entityAlias);
//
//        if (result.hasErrors()) {
//            trataErro(result, attributes);
//            cadastroEntity.addObject("erros", erros + result.getFieldError());
//
//        } else {
//            entity = getservice().edit(entity);
//            System.out.println("edit: " + entityAlias);
//            attributes.addFlashAttribute("mensagem", mensagem);
//            cadastroEntity.addObject("mensagem", mensagem);
//        }
//        cadastroEntity.addObject("acao", "");
//
////        return cadastroEntity;
////        return new ModelAndView("cadastro" + entityAlias, entityAlias, entity);
////        return new ModelAndView("redirect:/" + entityAlias + "/" + "editar?id=" + getservice().getIdEntity(entity));
//        
//        return cadastroEntity;
//    }
//
//    @Transactional
//    @GetMapping(value = "delete")
//    public ModelAndView deletarEntity(HttpServletRequest request) {
//
//        UUID idf = UUID.fromString(request.getParameter("id"));
//        getservice().delete(idf);
//        
//        ModelAndView movimentacao = new ModelAndView("/private/"+ entityAlias+"/movimentacao/movimentacao" + entityAlias);
//
//
////        return new ModelAndView("redirect:/" + entityAlias + "/movimentacao");
//        
//        return movimentacao;
//    }
//
//    private void trataErro(BindingResult result, RedirectAttributes attributes) {
//        System.out.println("erro ao add Entidade: " + entityAlias + " erro: " + result.getObjectName());
//        System.out.println("erro ap add Entidade: " + entityAlias + " fields erro: " + result.getFieldError());
//        System.out.println("erro ao add Entidade: " + entityAlias + " outros erros global: " + result.getGlobalError());
//        System.out.println("erro ao add Entidade: " + entityAlias + " outros erros nestedPatch: " + result.getNestedPath());
//        attributes.addFlashAttribute("erros", "Erro ao Salvar." + result.getFieldError());
//    }
//
//}
