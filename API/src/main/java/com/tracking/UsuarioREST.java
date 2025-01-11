package com.tracking;

import com.tracking.DAO.*;
import com.tracking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UsuarioREST {
    @Autowired 
    private IUsuario usuarioDAO;
    
    @RequestMapping("/login")
    public Usuario Login(@RequestParam String nombreUsuario, @RequestParam String pass){
        List<Usuario> lista = usuarioDAO.findAll();
        Usuario retornoFalso = new Usuario();
        for (Usuario u: lista){
            if (u.getNombreUsuario().equals(nombreUsuario)){
                if (u.getPass().equals(pass)){
                    return u;
                }
                else {
                    retornoFalso.setId(Long.valueOf(401));
                    return retornoFalso;
                }
            }
        }
        retornoFalso.setId(Long.valueOf(404));
        return retornoFalso;
    }
    @RequestMapping("/registro")
    public boolean Registro(@RequestParam String nombreUsuario, @RequestParam String pass){
        List<Usuario> lista = usuarioDAO.findAll();
        for (Usuario u: lista){
            if (u.getNombreUsuario().equals(nombreUsuario)){
                return false;
            }
        }
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPass(pass);
        usuarioDAO.save(usuario);
        return true;
    }
    @RequestMapping("/modificar")
    public boolean Modificar(@RequestParam Long id, 
            @RequestParam String nombreUsuario, @RequestParam String pass){
        List<Usuario> lista = usuarioDAO.findAll();
        for (Usuario u: lista){
            if (u.getNombreUsuario().equals(nombreUsuario)){
                if (u.getId() != id)
                    return false;
            }
        }
        Usuario usuario = usuarioDAO.findById(id).get();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPass(pass);
        usuarioDAO.save(usuario);
        return true;
    }
}
