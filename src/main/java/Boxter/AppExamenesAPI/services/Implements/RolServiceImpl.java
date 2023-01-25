package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.Rol;
import Boxter.AppExamenesAPI.repository.RolRepository;
import Boxter.AppExamenesAPI.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Rol> obtenerRoles() {
        return rolRepository.findAll();
    }

}
