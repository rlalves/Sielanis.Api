package infra.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import infra.fabrica.*;
import modelo.dominio.Script;
import modelo.entidade.Pais;
import modelo.entidades.Livro;

public class PaisesRepositorio extends Repositorio {
	public PaisesRepositorio() {
		super();
	}
	
	public void InserirPaises(String comando, List<Pais> paises) {
		
		List<Object[]> dados = new ArrayList();
			
		for(Pais pais : paises) {
			Object[] o = new Object[4]; 
			o[0] = pais.getNome();
			o[1] = pais.getCapital();
			o[2] = pais.getLinguas();
			o[3] = pais.getPopulacao();
			
			dados.add(o);
		}
		
		super.Inserir(comando, dados);
	}
	
    public List<Pais> ConsultarPaises(String comando) {
    	
        List<Pais> paises = new ArrayList<>();
        
        try (Connection c = super.conexao.ObtemConexao();
            PreparedStatement preparedStatement = c.prepareStatement(comando)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("Nome");
                String capital = rs.getString("Capital");
                String linguas = rs.getString("Linguas");
                int populacao = rs.getInt("Populacao");
                paises.add(new Pais(nome, capital, linguas, populacao));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return paises;
    }
}
