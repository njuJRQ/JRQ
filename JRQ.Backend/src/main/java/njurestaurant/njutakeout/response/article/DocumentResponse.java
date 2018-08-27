package njurestaurant.njutakeout.response.article;

public class DocumentResponse {
	private DocumentItem document;

	public DocumentResponse(){
	}

	public DocumentResponse(DocumentItem document) {
		this.document = document;
	}

	public DocumentItem getDocument() {
		return document;
	}

	public void setDocument(DocumentItem document) {
		this.document = document;
	}
}
