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

    // Aqui guardo los IMC y los atletas
    @RequestMapping(value = "/insertar",  method = RequestMethod.GET)
    public String insertarPage(Model model) {
        model.addAttribute(new Atleta());
        return "insertar";
    }

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public String insertarAtleta(Atleta atleta, BindingResult result, Model model) {
        //guardo al atleta
        atletaService.save(atleta);

        // calculo del IMC
        IMC imc = new IMC();
        imc.setPeso(atleta.getPeso());
        imc.setFechaCalculo(new Date());
        imc.setImc(((imc.getPeso() / (atleta.getEstatura()*atleta.getEstatura()))*10000));
        imc.setAtleta(atleta);
        imcService.save(imc);

        return "index";
    }

    // aqui enlisto todos los atletas, los detalles y los imcs
    @RequestMapping(value = "/listar",  method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("atletas", atletaService.getAll());
        return "listar";
    }

    @RequestMapping(value = "/listarIMC",  method = RequestMethod.GET)
    public String listarIMC(Model model) {
        model.addAttribute("imcs", imcService.getAll());
        return "listarIMC";
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
        List<IMC> imcTemp = imcService.findByAtleta(id);
        model.addAttribute("imcs", imcTemp);
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
    @RequestMapping(value = "/editarAtleta/{id}", method = RequestMethod.GET)
    public String editar(Model model, @PathVariable long id) {

        Optional<Atleta> atleta = atletaService.get(id);
        if (atleta.isPresent()){
            model.addAttribute("atleta", atleta.get());
            return "editarAtleta";
        }
        return "index";
    }

    @RequestMapping(value = "/editarAtleta/{id}", method = RequestMethod.POST)
    public String guardarCambios(Atleta atleta, BindingResult result, Model model, @PathVariable long id) {
        Optional<Atleta> a = atletaService.get(atleta.getId());

        atleta.setNombre(a.get().getNombre());
        atleta.setPrimerApellido(a.get().getPrimerApellido());
        atleta.setSegundoApellido(a.get().getSegundoApellido());
        atleta.setFechaDeNacimiento(a.get().getFechaDeNacimiento());
        atleta.setDeporte(a.get().getDeporte());
        atleta.setRama(a.get().getRama());
        atleta.setEstatura(a.get().getEstatura());

        if (a.get().getPeso() != atleta.getPeso()) {
            double imcTotal = ((atleta.getPeso() / (atleta.getEstatura() * atleta.getEstatura()))*10000);
            IMC imc = new IMC(imcTotal, atleta.getPeso(), new Date(), atleta);
            imcService.save(imc);
        }

        atletaService.save(atleta);

        return "editarAtleta";
    }
}
