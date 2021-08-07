package com.cenfotec.deporte.controllers;

import com.cenfotec.deporte.domain.Atleta;
import com.cenfotec.deporte.domain.IMC;
import com.cenfotec.deporte.services.AtletaService;
import com.cenfotec.deporte.services.IMCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class AtletaController {
    @Autowired
    AtletaService atletaService;

    @Autowired
    IMCService imcService;

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value = "/insertar",  method = RequestMethod.GET)
    public String insertarPage(Model model) {
        model.addAttribute(new Atleta());
        return "insertar";
    }

    // Aqui guado los IMC y los atletas
    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public String insertarAtleta(Atleta atleta, BindingResult result, Model model) {
        //guardo al atleta
        atletaService.save(atleta);

        // calculo del IMC
        IMC imc = new IMC();
        imc.setEstatura(atleta.getEstatura());
        imc.setPeso(atleta.getPeso());
        imc.setFechaCalculo(new Date());
        imc.setImc(imc.getPeso() / Math.pow(imc.getEstatura(), 2));
        imc.setAtleta(atleta);
        imcService.save(imc);

        return "index";
    }

    // aqui enlisto tos atletas, los detalles y los imcs
    @RequestMapping(value = "/listar",  method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("atletas", atletaService.getAll());
        return "listar";
    }

    @RequestMapping(value = "/verAtleta/{id}",  method = RequestMethod.GET)
    public String verAtleta(Model model, @PathVariable long id) {
        Optional<Atleta> temAtleta =  atletaService.get(id);
        if(temAtleta.isPresent()){
            model.addAttribute("atleta", temAtleta.get());
            return "verAtleta";
        }
        return "index";
    }

    @RequestMapping(value = "/verIMC/{id}",  method = RequestMethod.GET)
    public String verIMC(Model model, @PathVariable long id) {
        List<IMC> temIMC =  imcService.getAll();
        model.addAttribute("imcs", temIMC.stream().filter(data -> data.getAtleta().getId() != id));
        return "verIMC";
    }

    // aqui busco atela por similitud de letra en el apellido o nombre
    @RequestMapping(value = "/buscarAtleta",  method = RequestMethod.GET)
    public String buscarAtletas(Model model) {
        model.addAttribute("atleta", new Atleta());
        return "buscarAtleta";
    }

    @RequestMapping(value = "/buscarAtleta",  method = RequestMethod.POST)
    public String buscarAtletas(Atleta atleta, BindingResult result, Model model) {
        model.addAttribute("atletas", atletaService.find(atleta.getNombre()));
        return "listar";
    }

    //Aqui edito el un atleta trayendo los datos
    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String editar(Model model, @PathVariable long id) {

        Optional<Atleta> atleta = atletaService.get(id);
        if (atleta.isPresent()){
            model.addAttribute("atleta", atleta.get());
            return "editar";
        }
        return "index";
    }

    @RequestMapping(value = "/editarAtleta/{id}", method = RequestMethod.POST)
    public String guardarCambios(Atleta atleta, BindingResult result, Model model, @PathVariable long id) {
        Optional<Atleta> a = atletaService.get(atleta.getId());

        if (a.get().getPeso() != atleta.getPeso()) {
            // calculo del IMC
            IMC imc = new IMC();
            imc.setEstatura(atleta.getEstatura());
            imc.setPeso(atleta.getPeso());
            imc.setFechaCalculo(new Date());
            imc.setImc(imc.getPeso() / Math.pow(imc.getEstatura(), 2));
            imc.setAtleta(atleta);
            imcService.save(imc);
        }
        atletaService.save(atleta);

        return "atleta";
    }
}
