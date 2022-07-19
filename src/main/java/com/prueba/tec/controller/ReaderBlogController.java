package com.prueba.tec.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.prueba.tec.entidades.Blogs;
import com.prueba.tec.entidades.BlogsReaders;
import com.prueba.tec.entidades.Readers;
import com.prueba.tec.request.ReaderBlogRequest;
import com.prueba.tec.response.ReaderPerBlogResponse;
import com.prueba.tec.service.IBlogReaderService;
import com.prueba.tec.service.IBlogService;
import com.prueba.tec.service.IReaderService;

@Controller
@RequestMapping("/reader-blog")
public class ReaderBlogController {
	
	@Autowired
	IBlogService blogService;
	
	@Autowired
	IReaderService readerService;
	
	@Autowired
	IBlogReaderService blogReaderService;
	
	private List<ReaderPerBlogResponse> cargaInicial(){
		List<BlogsReaders> lista = blogReaderService.findAll();
    	List<ReaderPerBlogResponse> listRetorno = new ArrayList<>();
    	for (BlogsReaders blogsreader : lista) {
    		ReaderPerBlogResponse pojo = new ReaderPerBlogResponse();
    		pojo.setbIdRes(blogsreader.getbId());
    		pojo.setrIdRes(blogsreader.getrId());
    		Blogs blogs = blogService.findRecordById(blogsreader.getbId());
    		pojo.setNameBlogRes(blogs.getTitle());
    		Readers reader = readerService.findRecordById(blogsreader.getrId());
    		pojo.setNameReaderRes(reader.getName());
    		listRetorno.add(pojo);
		}
    	return listRetorno;
	}
	
	
    @GetMapping("/reader-blog-menu")
    public String menu(Model model) {
     List<ReaderPerBlogResponse> cargaInicial = cargaInicial();
     if(cargaInicial.isEmpty()) {
    	 model.addAttribute("conjunto", null);
     }else {
    	 model.addAttribute("conjunto", cargaInicial);
     }
      return "readers-per-blog";
    }
    
    @GetMapping("/readers-per-agregar")
    public String agregar(Model model) {
      List<Readers> readers = readerService.findAll();
      List<Blogs> blogs = blogService.findAll();
      
      model.addAttribute("readers", readers); 
      model.addAttribute("blogs", blogs);
      
      model.addAttribute("request", new ReaderBlogRequest() );
      return "add-blogs-readers";
    }
    
    @GetMapping("/readers-blogs-modificar/{idBlog}/{idReader}")
    public String modificar(@PathVariable("idBlog") long idBlog,@PathVariable("idReader") long idReader,Model model) {
     BlogsReaders findRecordById = blogReaderService.findRecordById(idBlog,idReader);
     ReaderBlogRequest pojo = parseoInverso(findRecordById);
     List<Readers> readers = readerService.findAll();
     List<Blogs> blogs = blogService.findAll();
     
     model.addAttribute("readers", readers); 
     model.addAttribute("blogs", blogs);
     model.addAttribute("request", pojo); 
      return "add-blogs-readers";
    }
    
    @GetMapping("/readers-blogs-eliminar/{idBlog}/{idReader}")
    public String eliminar(@PathVariable("idBlog") long idBlog,@PathVariable("idReader") long idReader,Model model) {
     BlogsReaders findRecordById = blogReaderService.findRecordById(idBlog,idReader);
     blogReaderService.delete(findRecordById);

     model.addAttribute("conjunto", cargaInicial()); 
     return "readers-per-blog";
    }
    
    
    @PostMapping("/guardar-conjunto")
    public String guardar(@Valid @ModelAttribute("request") ReaderBlogRequest request, BindingResult result, Model model) {
        BlogsReaders entidad = parseo(request);
        blogReaderService.save(entidad);
    	
        model.addAttribute("conjunto", cargaInicial()); 
        return "readers-per-blog";
    }
    
    private BlogsReaders parseo(ReaderBlogRequest request) {
    	BlogsReaders entidad = new BlogsReaders();
    	entidad.setbId(request.getBlogId());
    	entidad.setrId(request.getReaderId());
    	return entidad;
    	
    }

    private ReaderBlogRequest parseoInverso(BlogsReaders request) {
    	ReaderBlogRequest entidad = new ReaderBlogRequest();
    	entidad.setBlogId(request.getbId());
    	entidad.setReaderId(request.getrId());
    	return entidad;
    	
    }
}
