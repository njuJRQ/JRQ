package njurestaurant.njutakeout.response.article.document;

import java.util.List;

public class DocumentListResponse {
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
