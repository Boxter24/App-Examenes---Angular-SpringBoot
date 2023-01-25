package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.Rol;
import Boxter.AppExamenesAPI.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Rol> listarRoles(){
        return rolService.obtenerRoles();
    }

}
