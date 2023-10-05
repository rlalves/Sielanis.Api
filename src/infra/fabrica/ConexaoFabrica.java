package infra.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFabrica {
	private static String _jdbcURL;
	private static String _jdbcUsuario; 
	private static String _jdbcSenha;
	
	public ConexaoFabrica(String jdbcURL, String jdbcUsuario, String jdbcSenha) {
		_jdbcURL = jdbcURL;
		_jdbcUsuario = jdbcUsuario;
		_jdbcSenha = jdbcSenha;
    }
	
    public Connection ObtemConexao() {
       	
    	Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(_jdbcURL, _jdbcUsuario, _jdbcSenha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexao;
    }
    
    public void FecharConexao() {
    	
    }
}
