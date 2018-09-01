package njurestaurant.njutakeout.response.article.document;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class DocumentListResponse extends Response{
	private List<DocumentItem> documents;

	public DocumentListResponse(){
	}

	public DocumentListResponse(List<DocumentItem> documents) {
		this.documents = documents;
	}

	public List<DocumentItem> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentItem> documents) {
		this.documents = documents;
	}
}
