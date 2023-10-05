package infra.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infra.fabrica.ConexaoFabrica;
import modelo.dominio.Script;
import modelo.entidade.Pais;
import modelo.entidades.Livro;

public class Repositorio {
	protected ConexaoFabrica conexao;

	public Repositorio() {
		this.conexao = new ConexaoFabrica("jdbc:h2:~/paises", "sa", "");
	}
	
	public void ExecutarComando(String comando) {
		try (Connection c = conexao.ObtemConexao();
		        PreparedStatement preparedStatement = c.prepareStatement(comando)) {
				preparedStatement.executeUpdate();
				c.close();
			}
			catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void Inserir(String comando, List<Object[]> dados) {
		try (Connection c = conexao.ObtemConexao();
		    PreparedStatement preparedStatement = c.prepareStatement(comando)) {
			
			for (Object[] registro : dados) {
			    preparedStatement.setString(1, (String) registro[0]); 
			    preparedStatement.setString(2, (String) registro[1]); 
			    preparedStatement.setString(3, (String) registro[2]); 
			    preparedStatement.setInt(4, (int) registro[3]);
			    preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			c.close();
			
			}
			catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
