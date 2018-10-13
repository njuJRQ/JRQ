package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.DocumentDao;
import njurestaurant.njutakeout.dataservice.article.DocumentDataService;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentDataServiceImpl implements DocumentDataService {
	private final DocumentDao documentDao;

	@Autowired
	public DocumentDataServiceImpl(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	@Override
	public boolean isDocumentExistent(String id) {
		return documentDao.existsById(id);
	}

	@Override
	public void saveDocument(Document document) {
		documentDao.save(document);
	}

	@Override
	public void addDocument(Document document) {
		documentDao.save(document);
	}

	@Override
	public Document getDocumentById(String id) throws NotExistException {
		Optional<Document> optionalDocument = documentDao.findById(id);
		if (optionalDocument.isPresent()) {
			return optionalDocument.get();
		} else {
			throw new NotExistException("Document ID", id);
		}
	}

	@Override
	public List<Document> getAllDocuments() {
		return documentDao.findAll();
	}

	@Override
	public void deleteDocumentById(String id) throws NotExistException {
		if (documentDao.existsById(id)) {
			documentDao.deleteById(id);
		} else {
			throw new NotExistException("Document ID", id);
		}
	}

	@Override
	public List<Document> getMyDocumentListBefore(String openid, String id) throws NotExistException {
		List<Document> documents = null;
		if (id.equals("")) {
			documents = documentDao.findTop10ByOrderByTimeStampDesc();
		} else {
			Document document = getDocumentById(id);
			documents = documentDao.findTop10ByTimeStampBeforeOrderByTimeStampDesc(document.getTimeStamp());
		}

		if (!documents.isEmpty()) {
			List<Document> sameStampDocuments = documentDao.findDocumentsByTimeStamp(documents.get(documents.size()-1).getTimeStamp());
			for(Document ssd:sameStampDocuments) {
				boolean flag = false; //标记ssd是否在documents中
				for(Document d:documents){
					if(ssd.getId().equals(d.getId())){
						flag = true;
						break;
					}
				}
				if(!flag){ //ssd不在documents里面，加入进去
					documents.add(ssd);
				}
			}
		}
		return documents;
	}
}
