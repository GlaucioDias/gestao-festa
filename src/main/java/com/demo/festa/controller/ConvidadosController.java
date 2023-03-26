package com.demo.festa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.festa.model.Convidado;
import com.demo.festa.repository.Convidados;

@Controller
@RequestMapping("/")
public class ConvidadosController {

	@Autowired
	private Convidados convidados;

	@RequestMapping(value = { "/", "/convidados" }, method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ListaConvidados");
		modelAndView.addObject("convidados", convidados.findAll());		
		modelAndView.addObject(new Convidado());
		return modelAndView;
	}
	
	@RequestMapping(value = { "/", "/convidados" }, method = RequestMethod.POST)
	public String salvar(@ModelAttribute Convidado convidado){
		this.convidados.save(convidado);
		return "redirect:/";
	}

}
