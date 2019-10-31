package nlp.frba.utn.documents.exceptions.classes;

public class NERTagNotFoundException extends GenericException {
	private static final long serialVersionUID = 2469601116860314620L;

	public NERTagNotFoundException(String tagName) {
		super("NERTag not found");
		addDetail("tag_name", tagName);
	}

}
