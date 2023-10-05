package aplicacao;

import java.util.List;

import infra.api.*;
import infra.repositorio.*;
import modelo.fabrica.*;
import modelo.dominio.*;
import modelo.entidade.*;

public class PaisesApp {
	
	PaisesRepositorio repositorio;
	ScriptFabrica script;
	
	public PaisesApp() {
		repositorio = new PaisesRepositorio();
		script = new ScriptFabrica();
	}
	
	public void Executar() {
		PaisesApi api = new PaisesApi();
		List<Pais> paises01 = api.ObtemPaises();
		
		//repositorio.ExecutarComando(script.ObtemScript(Script.criar));
		repositorio.InserirPaises(script.ObtemScript(Script.inserir), paises01);
		List<Pais> paises02 = repositorio.ConsultarPaises(script.ObtemScript(Script.consultar));
		repositorio.ExecutarComando(script.ObtemScript(Script.destruir));
	}
	
}
