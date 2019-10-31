package nlp.frba.utn.documents.exceptions.classes;

public class UnknownErrorException extends GenericException {
	private static final long serialVersionUID = 1059954840719904733L;

	public UnknownErrorException() {
		super("An unknown error has ocurred");
	}

}
