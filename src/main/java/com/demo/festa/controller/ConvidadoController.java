package com.demo.festa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.festa.model.Convidado;
import com.demo.festa.repository.Convidados;

@Controller
@RequestMapping("/")
public class ConvidadoController {

	@Autowired
	private Convidados convidados;

	@RequestMapping(value = { "/", "/convidados" }, method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Convidado> listaConvidados = this.convidados.findAll();
		ModelAndView modelAndView = new ModelAndView("ListaConvidados");
		modelAndView.addObject("convidados", listaConvidados);		
		modelAndView.addObject(new Convidado());
		return modelAndView;
	}
	
	@RequestMapping(value = { "/", "/convidados" }, method = RequestMethod.POST)
	public String salvar(@ModelAttribute Convidado convidado){
		this.convidados.save(convidado);
		return "redirect:/";
	}
	
	@RequestMapping(value="/convidado/excluir", method = RequestMethod.POST)
	public String removeConvidado(@RequestParam("id") long id) {
		this.convidados.deleteById(id);
		return "redirect:/";
	}
	

}
