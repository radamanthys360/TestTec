package com.prueba.tec.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.prueba.tec.entidades.Blogs;
import com.prueba.tec.request.BlogRequest;
import com.prueba.tec.service.IBlogService;

@Controller
public class BlogController {
	
	@Autowired
	IBlogService blogService;
	
    @GetMapping("/blogs-menu")
    public String readerMenu(Model model) {
    	List<Blogs> lista = blogService.findAll();
    	
      model.addAttribute("blogs", lista); 
      return "blogs";
    }
    
    @GetMapping("/blogs-agregar")
    public String readerAgregar(Model model) {
      model.addAttribute("request", new BlogRequest()); 
      return "add-blogs";
    }
    
    @PostMapping("/guardar-blog")
    public String addUser(@Valid @ModelAttribute("request") BlogRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        Blogs entidad = parseo(request);
        blogService.save(entidad);
        
    	List<Blogs> lista = blogService.findAll();
    	
        model.addAttribute("blogs", lista); 
        return "blogs";
    }
    
    @GetMapping("/blogs-modificar/{id}")
    public String readerModificar(@PathVariable("id") long id,Model model) {
    	Blogs entidad = blogService.findRecordById(id);
    	BlogRequest pojo = parseoInverso(entidad);
      model.addAttribute("request", pojo); 
      return "add-blogs";
    }
    
    @GetMapping("/blogs-eliminar-c/{id}")
    public String readerEliminar1(@PathVariable("id") long id,Model model,RedirectAttributes redirectAttrs) {
    	Blogs findRecordById = blogService.findRecordById(id);
    	blogService.delete(findRecordById);
    	List<Blogs> lista = blogService.findAll();
    	
        model.addAttribute("blogs", lista); 
        return "blogs";
    }
    
	private Blogs parseo(BlogRequest request) {
		Blogs entidad = new Blogs();
		entidad.setPkBlog(request.getPkBlogInput());
		entidad.setTitle(request.getTitleInput());
		entidad.setDescription(request.getDescriptionInput());
		return entidad;
		
	}
	
	private BlogRequest parseoInverso(Blogs request) {
		BlogRequest pojo = new BlogRequest();
		pojo.setPkBlogInput(request.getPkBlog());
		pojo.setDescriptionInput(request.getDescription());
		pojo.setTitleInput(request.getTitle());
		return pojo;
		
	}

}
