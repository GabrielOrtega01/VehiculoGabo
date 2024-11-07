package com.mongodbvehiculosgabo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodbvehiculosgabo.app.entity.Administrador;
import com.mongodbvehiculosgabo.app.entity.Cliente;
import com.mongodbvehiculosgabo.app.entity.Vehiculo;
import com.mongodbvehiculosgabo.app.entity.Reserva;
import com.mongodbvehiculosgabo.app.entity.Pago;
import com.mongodbvehiculosgabo.app.exception.NotFoundException;
import com.mongodbvehiculosgabo.app.repository.AdministradorRepository;
import com.mongodbvehiculosgabo.app.repository.ClienteRepository;
import com.mongodbvehiculosgabo.app.repository.VehiculoRepository;
import com.mongodbvehiculosgabo.app.repository.ReservaRepository;
import com.mongodbvehiculosgabo.app.repository.PagoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "administrador")
public class ControllerWebAdministrador {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private PagoRepository pagoRepository;

    // Gestión de administrador y cliente

    @GetMapping("/index")
    public String administradorIndexTemplate(Model model, HttpSession session) {
        Administrador administrador = (Administrador) session.getAttribute("usuarioLogeado");
        if (administrador != null) {
            model.addAttribute("usuario", administrador.getUsuario());
            model.addAttribute("nombre", administrador.getNombre());
        }
        return "index-administrador";
    }
    
    @GetMapping("/login")
    public String administradorLoginTemplate(Model model) {
        return "login-administrador";
    }
    
    @PostMapping("/logear")
    public String administradorLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        // Buscar al administrador por nombre de usuario en la base de datos
        Administrador administrador = null;
        for (Administrador c : administradorRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
                administrador = c;
                break;
            }
        }
        
        // Verificar si se encontró al administrador y si la contraseña es correcta
        if (administrador != null && administrador.getContrasena().equals(contrasena)) {
            // Guardar el usuario logeado en la sesión
            session.setAttribute("usuarioLogeado", administrador);
            // Si las credenciales son correctas, redirigir a la página de inicio
            return "redirect:/administrador/index";
        } else {
            // Si las credenciales son incorrectas, mostrar un mensaje de error y volver al formulario de login
            model.addAttribute("error", true);
            return "login-administrador";
        }
    }

    @GetMapping("/cliente/crear")
    public String administradorCrearClienteTemplate(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @GetMapping("/lista")
    public String clienteListTemplate(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "cliente-lista";
    }

    @GetMapping("/cliente/edit/{id}")
    public String administradorEditClienteTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("cliente", clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado")));
        return "cliente-form";
    }

    @PostMapping("/cliente/save")
    public String administradorSaveClienteProcess(@ModelAttribute("cliente") Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/administrador/index";
    }

    @GetMapping("/cliente/delete/{id}")
    public String administradorDeleteClienteProcess(@PathVariable("id") String id) {
        clienteRepository.deleteById(id);
        return "redirect:/administrador/lista";
    }

    // Gestión de Vehículos

    @GetMapping("/vehiculo/crear")
    public String administradorCrearVehiculoTemplate(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculo-form";
    }

    @GetMapping("/vehiculo/lista")
    public String vehiculoListTemplate(Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "vehiculo-lista";
    }

    @GetMapping("/vehiculo/edit/{id}")
    public String administradorEditVehiculoTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("vehiculo", vehiculoRepository.findById(id).orElseThrow(() -> new NotFoundException("Vehículo no encontrado")));
        return "vehiculo-form";
    }

    @PostMapping("/vehiculo/save")
    public String administradorSaveVehiculoProcess(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
        return "redirect:/administrador/vehiculo/lista";
    }

    @GetMapping("/vehiculo/delete/{id}")
    public String administradorDeleteVehiculoProcess(@PathVariable("id") String id) {
        vehiculoRepository.deleteById(id);
        return "redirect:/administrador/vehiculo/lista";
    }

    // Gestión de Reservas

    @GetMapping("/reserva/crear")
    public String administradorCrearReservaTemplate(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "reserva-form";
    }

    @GetMapping("/reserva/lista")
    public String reservaListTemplate(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reserva-lista";
    }

    @PostMapping("/reserva/save")
    public String administradorSaveReservaProcess(@ModelAttribute("reserva") Reserva reserva) {
        reservaRepository.save(reserva);
        return "redirect:/administrador/reserva/lista";
    }

    @GetMapping("/reserva/delete/{id}")
    public String administradorDeleteReservaProcess(@PathVariable("id") String id) {
        reservaRepository.deleteById(id);
        return "redirect:/administrador/reserva/lista";
    }

    // Gestión de Pagos

    @GetMapping("/pago/crear")
    public String administradorCrearPagoTemplate(Model model) {
        model.addAttribute("pago", new Pago());
        model.addAttribute("reservas", reservaRepository.findAll());
        return "pago-form";
    }

    @GetMapping("/pago/lista")
    public String pagoListTemplate(Model model) {
        model.addAttribute("pagos", pagoRepository.findAll());
        return "pago-lista";
    }

    @PostMapping("/pago/save")
    public String administradorSavePagoProcess(@ModelAttribute("pago") Pago pago) {
        pagoRepository.save(pago);
        return "redirect:/administrador/pago/lista";
    }

    @GetMapping("/pago/delete/{id}")
    public String administradorDeletePagoProcess(@PathVariable("id") String id) {
        pagoRepository.deleteById(id);
        return "redirect:/administrador/pago/lista";
    }
}
