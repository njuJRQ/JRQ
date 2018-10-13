package njurestaurant.njutakeout.bl.article.document;

import njurestaurant.njutakeout.blservice.article.document.DocumentBlService;
import njurestaurant.njutakeout.dataservice.article.DocumentDataService;
import njurestaurant.njutakeout.dataservice.article.LikeDataService;
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
	private final LikeDataService likeDataService;

	@Autowired
	public DocumentBlServiceImpl(DocumentDataService documentDataService, LikeDataService likeDataService) {
		this.documentDataService = documentDataService;
		this.likeDataService = likeDataService;
	}

	@Override
	public InfoResponse addDocument(String title, String content, String writerName, long likeNum) {
		documentDataService.addDocument(new Document(title, content, writerName, System.currentTimeMillis(), likeNum));
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
	public InfoResponse updateDocument(String id, String title, String content, String writerName, long likeNum) throws NotExistException {
		Document document = documentDataService.getDocumentById(id);
		document.setTitle(title);
		document.setContent(content);
		document.setWriterName(writerName);
		document.setTimeStamp(System.currentTimeMillis());
		document.setLikeNum(likeNum);
		documentDataService.saveDocument(document);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteDocument(String id) throws NotExistException {
		documentDataService.deleteDocumentById(id);
		return new InfoResponse();
	}

	@Override
	public DocumentResponse getMyDocument(String openid, String documentId) throws NotExistException {
		boolean hasLiked = likeDataService.isLikeExistent(openid, "document", documentId);
		return new DocumentResponse(new DocumentItem(documentDataService.getDocumentById(documentId), hasLiked));
	}

	@Override
	public DocumentListResponse getMyDocumentList(String openid) {
		List<Document> documents = documentDataService.getAllDocuments();
		List<DocumentItem> documentItems = new ArrayList<>();
		for(Document document:documents) {
			boolean hasLiked = likeDataService.isLikeExistent(openid, "document", document.getId());
			documentItems.add(new DocumentItem(document, hasLiked));
		}
		return new DocumentListResponse(documentItems);
	}
}
