package nlp.frba.utn.documents.utils;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupHousekeeper {

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		
	}
}