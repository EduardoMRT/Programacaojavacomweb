package br.com.eduardo.drogaria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		Faces.getSessionAttribute(null);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
