package njurestaurant.njutakeout.response.article.document;

import njurestaurant.njutakeout.response.Response;

public class DocumentResponse extends Response{
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
