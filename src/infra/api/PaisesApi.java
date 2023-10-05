package infra.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import modelo.entidade.*;

public class PaisesApi {
	
	public PaisesApi() {
		
	}
	
	public List<Pais> ObtemPaises(){
		
		List<Pais> paises = new ArrayList();
		
        // URL da API que você deseja acessar
        String apiUrl = "https://restcountries.com/v3.1/all";

        // Cria uma URL a partir da string da API
        URL url;
        
		try {
			// URL da API que você deseja acessar
			url = new URL(apiUrl);
			
			// Cria uma URL a partir da string da API
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        
	        // Abre uma conexão HTTP
	        connection.setRequestMethod("GET");
	        
            // Lê a resposta da API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String linha;
            StringBuilder resposta = new StringBuilder();
            
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();
            
            paises = TransformaTextoEmLista(resposta.toString());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return paises;
	}
	
	private List<Pais> TransformaTextoEmLista(String resposta){
		
		List<Pais> paises = new ArrayList();
		String parcial = resposta;
		int ponteiro = 0;
		boolean continua = true;
		
		String nome = "";
		String capital = "";
		String linguasT = "";
		List<String> linguasL;
		String linguas = "";
		int populacao = 0;
				
		while (continua) {
			ponteiro = parcial.indexOf("\"name\":{\"common\":\"");
			parcial = parcial.substring(ponteiro + 18);
			ponteiro = parcial.indexOf("\"");
			nome = parcial.substring(0, ponteiro);
			
			ponteiro = parcial.indexOf("\"capital\":[\"");
			parcial = parcial.substring(ponteiro + 12);
			ponteiro = parcial.indexOf("\"");
			capital = parcial.substring(0, ponteiro);
			
			ponteiro = parcial.indexOf("\"languages\":{");
			parcial = parcial.substring(ponteiro + 13);
			ponteiro = parcial.indexOf("},\"translations\"");
			linguasT = parcial.substring(0, ponteiro);
			linguasL = Arrays.asList(linguasT.split(","));
			for (String lingua : linguasL){
				linguas = linguas + "," + lingua.substring(6);
			}
			if (linguas.length() > 2040)
				linguas = linguas.substring(0, 2040);
			linguas = linguas.replace("\"", "");
			
			ponteiro = parcial.indexOf("\"population\":");
			parcial = parcial.substring(ponteiro + 13);
			ponteiro = parcial.indexOf(",");
			populacao = Integer.valueOf(parcial.substring(0, ponteiro));
			
			Pais pais = new Pais(nome, capital, linguas, populacao);
			paises.add(pais);
			
			if (parcial.indexOf("\"name\":{\"common\":\"") == -1)
				continua = false;
		}
		
		return paises;
	}
	
}
