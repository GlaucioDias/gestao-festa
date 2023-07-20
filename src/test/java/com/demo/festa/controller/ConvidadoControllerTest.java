package com.demo.festa.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.demo.festa.model.Convidado;
import com.demo.festa.repository.Convidados;

import io.restassured.http.ContentType;

@WebMvcTest
public class ConvidadoControllerTest {

	@Autowired
	private ConvidadoController convidadoController;

	@MockBean
	private Convidados convidados;

	private Convidado convidado = new Convidado();

	private MockMvc mockMvc;
	@BeforeEach
	public void setup() {
		standaloneSetup(this.convidadoController);

		convidado.setId(0);
		convidado.setNome("Paulo");
		convidado.setQuantidadeAcompanhantes(1);
	}

	@Test
	public void deveRedirecionarParaConvidados_QuandoEnderecoRaizForChamado() {
		given().get("/").thenReturn().getMockHttpServletResponse().getRedirectedUrl().equals("/convidados");
	}

	@Test
	public void deveRetornarLista_QuandoFindAllForChamado() {
		when(this.convidados.findAll()).thenReturn(List.of(convidado));
		
		List<Convidado> listaConvidados = this.convidados.findAll();
		
		assertEquals(List.of(convidado), listaConvidados);
		verify(this.convidados).findAll();
	}

	@Test
	public void deveRetornarSucesso_QuandoListarConvidados() {
		ModelAndView modelAndView = new ModelAndView();
		when(this.convidados.findAll()).thenReturn(List.of(convidado));

		modelAndView.addObject("convidados", this.convidados.findAll());

		given().accept(ContentType.URLENC).when().get("/convidados").then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarSucesso_QuandoSalvarConvidado() {
		given().accept(ContentType.URLENC).when().post("/convidados")
				.then().statusCode(HttpStatus.FOUND.value());
	}

	@Test
	public void deveSalvar_QuandoReceberUmConvidado() {
		convidado.setNome("José");
		convidado.setQuantidadeAcompanhantes(1);

		this.convidados.save(convidado);

		when(this.convidados.findAll()).thenReturn(List.of(convidado));

		List<Convidado> listaConvidados = this.convidados.findAll();

		this.convidados.findAll();
		


	}
}


		

