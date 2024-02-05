package br.com.eduardo.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//http://localhost:8080/Drogaria/[NomeRest] [Resources]
@ApplicationPath("rest")
public class DrogariaResourcesConfig extends ResourceConfig{
	 public DrogariaResourcesConfig() {
		 packages("br.com.eduardo.drogaria.service");
	 }
}
