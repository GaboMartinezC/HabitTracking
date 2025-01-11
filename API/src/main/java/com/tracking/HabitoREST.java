package com.tracking;

import com.tracking.DAO.*;
import com.tracking.model.*;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class HabitoREST {
    @Autowired 
    private IHabito habitoDAO;
    @Autowired
    private ICumplimiento cumplimientoDAO;
    
    private boolean ValidarModeloHabito(Habito habito){
        boolean retVal = true;
        if (habito.getPlazo()>0 && habito.getDiasDescanso()>0)
        {
            if (habito.getPlazo() < habito.getDiasDescanso())
                retVal = false;
            
        } else
            retVal = false;
        return retVal;
    }
    @GetMapping("/habitosUsuario/{idUsuario}")
    public List<Habito> BuscarHabitosUsuario(@PathVariable int idUsuario)
    {
        List<Habito> lista = habitoDAO.findAll();
        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getIdUsuario() != idUsuario){
                lista.remove(i);
            }
        }
        return lista;
    }
    @RequestMapping("/guardarHabito")
    public boolean GuardarHabito(@RequestParam String descripcion, @RequestParam int idUsuario,
            @RequestParam int plazo, @RequestParam int diasDescanso)
    {
        LocalDate currentDate = LocalDate.now();
        boolean retVal = true;
        Habito habito = new Habito();
        habito.setDescripcion(descripcion);
        habito.setIdUsuario(idUsuario);
        habito.setPlazo(plazo);
        habito.setDiasDescanso(diasDescanso);
        habito.setFechaInicio(currentDate.toString());
        if (ValidarModeloHabito(habito))
            habitoDAO.save(habito);
        else 
            return false;
        return true;
    }
    @RequestMapping("/marcarCumplimiento/{idHabito}")
    public boolean MarcarDiaCumplimiento(@PathVariable int idHabito){
        LocalDate currentDate = LocalDate.now();
        List<Cumplimiento> listaCumplimiento = cumplimientoDAO.findAll();
        for (Cumplimiento c: listaCumplimiento){
            if(c.getIdHabito() == idHabito){
                if (c.getFecha().toString().equals(currentDate.toString())){
                    return false;
                }
            }
        }
        Cumplimiento cumplimiento = new Cumplimiento();
        cumplimiento.setIdHabito(idHabito);
        cumplimiento.setFecha(currentDate.toString());
        cumplimientoDAO.save(cumplimiento);
        return true;
        
    }
    @RequestMapping("/modificarHabito")
    public boolean ModificarHabito(@RequestParam Long id, @RequestParam String descripcion, @RequestParam int plazo,
            @RequestParam int diasDescanso, @RequestParam String fecha)
    {
        if (!habitoDAO.findById(id).isPresent()){
            return false;
        }
        Habito habito = habitoDAO.findById(id).get();
        habito.setDescripcion(descripcion);
        habito.setPlazo(plazo);
        habito.setDiasDescanso(diasDescanso);
        habito.setFechaInicio(fecha);
        
        if (ValidarModeloHabito(habito))
            habitoDAO.save(habito);
        else
            return false;
        return true;
    }
    @RequestMapping("/eliminarHabito/{id}")
    public void EliminarHabito(@PathVariable Long id)
    {
        List<Cumplimiento> listaCumplimiento = cumplimientoDAO.findAll();
        int idEntero = id.intValue();
        for (Cumplimiento c: listaCumplimiento){
            if (c.getIdHabito() == idEntero){
                cumplimientoDAO.deleteById(c.getId());
            }
        }
        habitoDAO.deleteById(id);    
        
    }
}
