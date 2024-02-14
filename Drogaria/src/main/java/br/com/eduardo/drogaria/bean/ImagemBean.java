package br.com.eduardo.drogaria.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped //a imagem ira tentar ser recarregada a cada clique
public class ImagemBean {
	@ManagedProperty("#{param.caminho}")
	//guarda o caminho da foto
	private String caminho;
	//guarda os bytes da foto
	private StreamedContent foto;
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public StreamedContent getFoto() throws IOException {
		if(caminho == null || caminho.isEmpty()) {
			java.nio.file.Path path =  Paths.get("C:/eclipsejdk/Workspace/Uploads/branco.png");
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}else {
			java.nio.file.Path path =  Paths.get(caminho);
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}
		return foto;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	
	
}
