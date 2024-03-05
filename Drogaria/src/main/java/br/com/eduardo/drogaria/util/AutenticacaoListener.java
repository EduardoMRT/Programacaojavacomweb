package br.com.eduardo.drogaria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.eduardo.drogaria.bean.AutenticacaoBean;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		
		String paginaAtual = Faces.getViewId();
		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml") ? true : false; 
		if(ehPaginaDeAutenticacao == false) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		
			if(autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
