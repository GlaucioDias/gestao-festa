package com.demo.festa.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.festa.model.Convidado;
import com.demo.festa.repository.Convidados;

@Controller
@RequestMapping(value={"/"})
public class ConvidadoController {

	@Autowired
	private Convidados convidados;
	

    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/convidados";
    }

	@RequestMapping(value={"/convidados"}, method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Convidado> listaConvidados = this.convidados.findAll();
		ModelAndView modelAndView = new ModelAndView("ListaConvidados");
		modelAndView.addObject("convidados", listaConvidados);		
		modelAndView.addObject(new Convidado());
		return modelAndView;
	}
	
	@RequestMapping(value={"/convidados"}, method = RequestMethod.POST)
	public String salvar(@ModelAttribute Convidado convidado, RedirectAttributes redirectAttr){
		this.convidados.save(convidado);
		redirectAttr.addFlashAttribute("message", "Convidado adicionado!");
		return "redirect:/convidados";
	}
	
	@RequestMapping(value="/convidados/{id}/remover", method = RequestMethod.POST)
	public String removeConvidado(@PathVariable Long id, RedirectAttributes redirectAttr) {
		this.convidados.deleteById(id);
		redirectAttr.addFlashAttribute("message", "Convidado removido!");
		return "redirect:/convidados";
	}
	

}
