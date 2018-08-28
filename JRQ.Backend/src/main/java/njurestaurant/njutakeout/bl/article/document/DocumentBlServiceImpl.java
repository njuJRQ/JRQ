package njurestaurant.njutakeout.bl.article.document;

import njurestaurant.njutakeout.blservice.article.document.DocumentBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentResponse;

public class DocumentBlServiceImpl implements DocumentBlService {
	@Override
	public InfoResponse addDocument(String title, String writerName, String date, long likeNum) {
		return null;
	}

	@Override
	public DocumentResponse getDocument(long id) {
		return null;
	}

	@Override
	public DocumentListResponse getDocumentList() {
		return null;
	}

	@Override
	public InfoResponse updateDocument(long id, String title, String writerName, String date, long likeNum) {
		return null;
	}

	@Override
	public InfoResponse deleteDocument(long id) {
		return null;
	}
}
