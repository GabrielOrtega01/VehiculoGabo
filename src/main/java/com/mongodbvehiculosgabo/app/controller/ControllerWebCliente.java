package com.mongodbvehiculosgabo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import com.mongodbvehiculosgabo.app.entity.Cliente;
import com.mongodbvehiculosgabo.app.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "cliente")
	
public class ControllerWebCliente {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@GetMapping("/resultadoTotal")
    public String clienteResultadoTotalTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "cliente-resultado-total";
    }
	
	@GetMapping("/resultadoUnico")
    public String clienteResultadoUnicoTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "cliente-resultado";
    }
	
	@GetMapping("/index")
    public String clienteIndexTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Cliente cliente = (Cliente) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "index-cliente";
    }

	
	@GetMapping("/informe/{id}")
	public String verInformeCliente(@PathVariable("id") String id, Model model) {
	   
	    Cliente cliente = clienteRepository.findById(id).orElse(null);
	    
	    if (cliente != null) {
	       
	    	model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());	        
	    }
	    
	    return "informe-cliente"; 
	}

	
	@GetMapping("/ranking")
	public String mostrarRanking(Model model) {
	    // Obtener los 3 clientes con las notas más altas
	    List<Cliente> ranking = clienteRepository.findTop3ByOrderByPuntajeDesc(); // Asumiendo que 'puntaje' es el nombre del campo en tu entidad Cliente
	    model.addAttribute("ranking", ranking);
	    return "ranking"; // Devuelve la vista de ranking
	}

	
	
}
