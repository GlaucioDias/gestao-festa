package com.demo.festa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.festa.repository.Convidados;

@Controller
public class ConvidadosController {

	@Autowired
	private Convidados convidados;

	@GetMapping("/convidados")
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("ListaConvidados");

		modelAndView.addObject("convidados", convidados.findAll());

		return modelAndView;
	}
}
