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
import com.prueba.tec.entidades.Readers;
import com.prueba.tec.request.ReaderRequest;
import com.prueba.tec.service.IReaderService;

@Controller
public class ReaderController {
	
	@Autowired
	IReaderService readerService;
	
	
    @GetMapping("/readers-menu")
    public String readerMenu(Model model) {
    	List<Readers> lista = readerService.findAll();
    	
      model.addAttribute("readers", lista); 
      return "readers";
    }
    
    @GetMapping("/readers-agregar")
    public String readerAgregar(Model model) {
      model.addAttribute("request", new ReaderRequest()); 
      return "add-readers";
    }
    
    
    @GetMapping("/readers-modificar/{id}")
    public String readerModificar(@PathVariable("id") long id,Model model) {
    	Readers findRecordById = readerService.findRecordById(id);
    	ReaderRequest pojo = parseoInverso(findRecordById);
      model.addAttribute("request", pojo); 
      return "add-readers";
    }
    
    @GetMapping("/readers-eliminar-c/{id}")
    public String readerEliminar1(@PathVariable("id") long id,Model model,RedirectAttributes redirectAttrs) {
    	Readers findRecordById = readerService.findRecordById(id);
    	readerService.delete(findRecordById);
    	List<Readers> lista = readerService.findAll();
    	
        model.addAttribute("readers", lista); 
        return "readers";
    }

    @PostMapping("/guardar-reader")
    public String addUser(@Valid @ModelAttribute("request") ReaderRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        Readers entidad = parseo(request);
        readerService.save(entidad);
        
    	List<Readers> lista = readerService.findAll();
    	
        model.addAttribute("readers", lista); 
        return "readers";
    }
    
    
	private Readers parseo(ReaderRequest request) {
		Readers entidad = new Readers();
		entidad.setPkReader(request.getPkReaderInput());
		entidad.setName(request.getNameInput());
		return entidad;
		
	}
	
	private ReaderRequest parseoInverso(Readers entidad) {
		ReaderRequest pojo = new ReaderRequest();
		pojo.setPkReaderInput(entidad.getPkReader());
		pojo.setNameInput(entidad.getName());
		return pojo;
	}

}