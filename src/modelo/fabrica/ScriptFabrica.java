package modelo.fabrica;

import modelo.dominio.Script;

public class ScriptFabrica {
	
	public String ObtemScript(Script scripts)
	{
	    switch (scripts) {
	    case criar:
	        return "CREATE TABLE Paises (Nome VARCHAR(255), Capital VARCHAR(255),Linguas VARCHAR(2048), Populacao INT);";
	    case inserir:
	        return "INSERT INTO Paises (Nome, Capital, Linguas, Populacao) VALUES (?, ?, ?,?)";
	    case consultar:
	        return "SELECT * FROM Paises";
	    case destruir:
	        return "DROP TABLE Paises;";
	    }
	    return "";
	}
}
