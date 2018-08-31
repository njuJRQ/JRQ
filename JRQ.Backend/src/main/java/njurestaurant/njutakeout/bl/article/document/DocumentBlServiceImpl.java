package njurestaurant.njutakeout.bl.article.document;

import njurestaurant.njutakeout.blservice.article.document.DocumentBlService;
import njurestaurant.njutakeout.dataservice.article.DocumentDataService;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.document.DocumentItem;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentBlServiceImpl implements DocumentBlService {
	private final DocumentDataService documentDataService;

	@Autowired
	public DocumentBlServiceImpl(DocumentDataService documentDataService) {
		this.documentDataService = documentDataService;
	}

	@Override
	public InfoResponse addDocument(String title, String content, String writerName, String date, long likeNum) {
		documentDataService.addDocument(new Document(title, content, writerName, date, likeNum));
		return new InfoResponse();
	}

	@Override
	public DocumentResponse getDocument(String id) throws NotExistException {
		return new DocumentResponse(new DocumentItem(documentDataService.getDocumentById(id)));
	}

	@Override
	public DocumentListResponse getDocumentList() {
		List<Document> documents = documentDataService.getAllDocuments();
		List<DocumentItem> documentItems = new ArrayList<>();
		for(Document document:documents) {
			documentItems.add(new DocumentItem(document));
		}
		return new DocumentListResponse(documentItems);
	}

	@Override
	public InfoResponse updateDocument(String id, String title, String content, String writerName, String date, long likeNum) throws NotExistException {
		Document document = documentDataService.getDocumentById(id);
		document.setTitle(title);
		document.setContent(content);
		document.setWriterName(writerName);
		document.setDate(date);
		document.setLikeNum(likeNum);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteDocument(String id) throws NotExistException {
		documentDataService.deleteDocumentById(id);
		return new InfoResponse();
	}
}
