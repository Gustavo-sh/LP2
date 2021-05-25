package lab5;

/**
 * Classe que faz parte da validação total
 * do sistema. 
 * 
 * @author Gustavo Araujo Santos
 *
 */
public class Exceptions {

	public void verificaCpfRemocao(String cpf, String mensagem) {
		if (cpf == null) {
			throw new NullPointerException(mensagem + "cpf nao pode ser vazio ou nulo");
		} else if (cpf.equals("")) {
			throw new IllegalArgumentException(mensagem + "cpf nao pode ser vazio ou nulo");
		}
	}
	
	public void verificaCpf(String cpf, String mensagem) {
		if (cpf == null) {
			throw new NullPointerException(mensagem + "cpf nao pode ser vazio ou nulo.");
		} else if (cpf.equals("")) {
			throw new IllegalArgumentException(mensagem + "cpf nao pode ser vazio ou nulo.");
		} 
	}
	
	public void verificaNome(String nome, String mensagem) {
		if (nome == null) {
			throw new NullPointerException(mensagem + "nome nao pode ser vazio ou nulo.");
		} else if (nome.equals("")) {
			throw new IllegalArgumentException(mensagem + "nome nao pode ser vazio ou nulo.");
		}
	}
	
	public void verificaEmail(String email, String mensagem) {
		if (email == null) {
			throw new NullPointerException(mensagem + "email nao pode ser vazio ou nulo.");
		} else if (email.equals("")) {
			throw new IllegalArgumentException(mensagem + "email nao pode ser vazio ou nulo.");
		}
	}
    
	public void verificaLocalizacao(String localizacao, String mensagem) {
		if (localizacao == null) {
			throw new NullPointerException(mensagem + "localizacao nao pode ser vazia ou nula.");
		} else if (localizacao.equals("")) {
			throw new IllegalArgumentException(mensagem + "localizacao nao pode ser vazia ou nula.");
		}
	}
	
	public void validacaoNoCadastroCliente(String cpf, String nome, String email, String localizacao, String mensagem) {
		verificaCpf(cpf, mensagem);
		if(cpf.length() > 11 || cpf.length() < 11) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf invalido.");
		}
		verificaNome(nome, mensagem);
		verificaEmail(email, mensagem);
		verificaLocalizacao(localizacao, mensagem);
	}
	
	public void verificaAtributo(String atributo, String mensagem) {
		if (atributo == null) {
			throw new NullPointerException(mensagem + "atributo nao pode ser vazio ou nulo.");
		} else if (atributo.equals("")) {
			throw new IllegalArgumentException(mensagem + "atributo nao pode ser vazio ou nulo.");
		}
	}
	
	public void verificaNovoValor(String novoValor, String mensagem) {
		if (novoValor == null) {
			throw new NullPointerException(mensagem + "novo valor nao pode ser vazio ou nulo.");
		} else if (novoValor.equals("")) {
			throw new IllegalArgumentException(mensagem + "novo valor nao pode ser vazio ou nulo.");
		}
	}
	
	public void verificaTelefone(String telefone, String mensagem) {
		if (telefone == null) {
			throw new NullPointerException(mensagem + "telefone nao pode ser vazio ou nulo.");
		} else if (telefone.equals("")) {
			throw new IllegalArgumentException(mensagem + "telefone nao pode ser vazio ou nulo.");
		}
	}
	
	public void validaCadastroFornecedor(String nome, String email, String telefone, String mensagem) {
		verificaNome(nome, mensagem);
		verificaEmail(email, mensagem);
		verificaTelefone(telefone, mensagem);
	}
	
	public void verificaData(String data, String mensagem) {
		if (data == null) {
			throw new NullPointerException(mensagem + "data nao pode ser vazia ou nula.");
		} else if (data.equals("")) {
			throw new IllegalArgumentException(mensagem + "data nao pode ser vazia ou nula.");
		} else if (data.length() < 10 || data.length() > 10) {
			throw new IllegalArgumentException(mensagem + "data invalida.");
		}
		String finn1 = "";
		String finn2 = "";
		String finn3 = "";
	    finn1 += data.charAt(0);
	    finn1 += data.charAt(1);
	    finn2 += data.charAt(3);
	    finn2 += data.charAt(4);
	    finn3 += data.charAt(6);
	    finn3 += data.charAt(7);
	    finn3 += data.charAt(8);
	    finn3 += data.charAt(9);
	    finn1.trim();
	    if (finn1.isEmpty()) {
	    	throw new IllegalArgumentException(mensagem + "data invalida.");
	    }
	    finn2.trim();
	    if (finn2.isEmpty()) {
	    	throw new IllegalArgumentException(mensagem + "data invalida.");
	    }
	    finn3.trim();
	    if (finn3.isEmpty()) {
	    	throw new IllegalArgumentException(mensagem + "data invalida.");
	    }
	    if (Integer.parseInt(finn1) > 31) {
	    	throw new IllegalArgumentException(mensagem + "data invalida.");
	    } else if (Integer.parseInt(finn2) > 12) {
	    	throw new IllegalArgumentException(mensagem + "data invalida.");
	    } else if (Integer.parseInt(finn3) > 2020) {
	    	throw new IllegalArgumentException(mensagem + "data invalida.");
	    }
	}
	
	public void verificaDescricao(String descricao, String mensagem) {
		if (descricao == null) {
			throw new NullPointerException(mensagem + "descricao nao pode ser vazia ou nula.");
		} else if (descricao.equals("")) {
			throw new IllegalArgumentException(mensagem + "descricao nao pode ser vazia ou nula.");
		}
	}
	
	public void validaCadastroDeCompra(String cpf, String fornecedor, String data, String nome, String descricao, String mensagem) {
		verificaCpf(cpf, mensagem);
		verificaFornecedor(fornecedor, mensagem);
		verificaData(data, mensagem);
		verificaNome(nome, mensagem);
		verificaDescricao(descricao, mensagem);
	}
	
	public void verificaFornecedor(String fornecedor, String mensagem) {
		if (fornecedor == null) {
			throw new NullPointerException(mensagem + "fornecedor nao pode ser vazio ou nulo.");
		} else if (fornecedor.equals("")) {
			throw new IllegalArgumentException(mensagem + "fornecedor nao pode ser vazio ou nulo.");
		}
	}
	
	public void verificaCriterio(String criterio, String mensagem) {
		if (criterio == null) {
			throw new NullPointerException(mensagem + "criterio nao pode ser vazio ou nulo.");
		} else if (criterio.equals("")) {
			throw new IllegalArgumentException(mensagem + "criterio nao pode ser vazio ou nulo.");
		}
	}
}
