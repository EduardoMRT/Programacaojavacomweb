package br.com.eduardo.drogaria.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Pessoa;
import br.com.eduardo.drogaria.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

@Getter
@Setter
public class UsuarioDAOTest {
		
	private List<Usuario> usuarios;
	public String senha;
	
	@Test	
	@Ignore
	public void salvar(){

		String senhaCripto;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(12L);
		
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		
		
		
		Usuario usuario = new Usuario();
		senhaCripto = DigestUtils.sha256Hex("teste12345");
		usuario.setSenha(senhaCripto);
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		
		usuario.setTipo('B');
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuário salvo com sucesso.");
	}
	
	
}	