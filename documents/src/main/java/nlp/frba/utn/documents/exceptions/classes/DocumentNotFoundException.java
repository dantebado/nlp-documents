package nlp.frba.utn.documents.exceptions.classes;

public class DocumentNotFoundException extends GenericException {
	private static final long serialVersionUID = 7457033474894683798L;
	
	public DocumentNotFoundException(String documentId) {
		super("Document not found");
		addDetail("document_id", documentId);
	}

}
