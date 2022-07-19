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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.prueba.tec.entidades.Readers;
import com.prueba.tec.request.ReaderRequest;
import com.prueba.tec.service.IReaderService;

@Controller
@RequestMapping("/reader")
public class ReaderController {
	
	@Autowired
	IReaderService readerService;
	
	
    @GetMapping("/readers-menu")
    public String menu(Model model) {
      List<Readers> lista = readerService.findAll();
      if(lista.isEmpty()) {
    	  model.addAttribute("readers", null);
      }else {
    	  model.addAttribute("readers", lista);
      }
      model.addAttribute("request", new ReaderRequest());
      return "readers";
    }
    
    @GetMapping("/readers-agregar")
    public String agregar(Model model) {
      model.addAttribute("request", new ReaderRequest()); 
      return "add-readers";
    }
    
    
    @GetMapping("/readers-modificar/{id}")
    public String modificar(@PathVariable("id") long id,Model model) {
    	Readers findRecordById = readerService.findRecordById(id);
    	ReaderRequest pojo = parseoInverso(findRecordById);
      model.addAttribute("request", pojo); 
      return "add-readers";
    }
    
    @GetMapping("/readers-eliminar-c/{id}")
    public String eliminar(@PathVariable("id") long id,Model model,RedirectAttributes redirectAttrs) {
    	Readers findRecordById = readerService.findRecordById(id);
    	readerService.delete(findRecordById);
    	List<Readers> lista = readerService.findAll();
    	
        model.addAttribute("readers", lista); 
        model.addAttribute("request", new ReaderRequest());
        return "readers";
    }

    @PostMapping("/guardar-reader")
    public String guardar(@Valid @ModelAttribute("request") ReaderRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-readers";
        }
        Readers entidad = parseo(request);
        readerService.save(entidad);
        
    	List<Readers> lista = readerService.findAll();
    	
        model.addAttribute("readers", lista); 
        model.addAttribute("request", new ReaderRequest());
        return "readers";
    }
    
    
    @PostMapping("/buscar-reader")
    public String buscar(@Valid @ModelAttribute("request") ReaderRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-readers";
        }

        List<Readers> busqueda = readerService.findByName(request.getNameInput());
        if(busqueda.isEmpty()) {
        	model.addAttribute("readers", null); 
        }else {
        	model.addAttribute("readers", busqueda); 
        }
        
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
