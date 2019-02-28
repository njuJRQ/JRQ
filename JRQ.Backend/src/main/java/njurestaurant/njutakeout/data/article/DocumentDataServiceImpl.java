package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.DocumentDao;
import njurestaurant.njutakeout.dataservice.article.DocumentDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
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
		Optional<Document> optionalDocument = documentDao.findById(id);
		if (optionalDocument.isPresent()) {
			Document document = optionalDocument.get();
			if (! new File(document.getAttachment()).delete()) {
				System.err.println("文档附件"+document.getAttachment()+"删除失败");
			}
			if (! new File(document.getPreview()).delete()) {
				System.err.println("文档附件预览图"+document.getPreview()+"删除失败");
			}
			documentDao.delete(document);
		} else {
			throw new NotExistException("Document ID", id);
		}
	}

	@Override
	public void deleteDocumentsByWriterName(String writerName) {
		List<Document> documents = documentDao.findDocumentsByWriterName(writerName);
		for (Document document:documents) {
			if (! new File(document.getAttachment()).delete()) {
				System.err.println("文档附件"+document.getAttachment()+"删除失败");
			}
			if (! new File(document.getPreview()).delete()) {
				System.err.println("文档附件预览图"+document.getPreview()+"删除失败");
			}
			documentDao.delete(document);
		}
	}

	@Override
	public List<Document> getMyDocumentListBefore(String openid, String id) throws NotExistException {
		return getMyDocumentListBeforeTimeStamp(openid,
				id.equals("")?-1:getDocumentById(id).getTimeStamp());
	}

	@Override
	public List<Document> getMyDocumentListBeforeTimeStamp(String openid, long timeStamp) throws NotExistException {
		List<Document> documents = null;
		if (timeStamp<0) {
			documents = documentDao.findTop10ByOrderByTimeStampDesc();
		} else {
			documents = documentDao.findTop10ByTimeStampBeforeOrderByTimeStampDesc(timeStamp);
		}

		if (!documents.isEmpty()) {
			List<Document> sameStampDocuments = documentDao.findDocumentsByTimeStamp(documents.get(documents.size()-1).getTimeStamp());
			addSame(documents, sameStampDocuments);
		}
		return documents;
	}

	@Override
	public List<Document> getTop10DocumentsOrderByLikeNum(String openid, String id) throws NotExistException {
		return getDocumentsOrderByLikeNumBefore(openid,
				id.equals("")?-1:getDocumentById(id).getLikeNum());
	}

	@Override
	public List<Document> getDocumentsOrderByLikeNumBefore(String openid, long likeNum) throws NotExistException {
		List<Document> documents = null;
		if(likeNum<0){
			documents = documentDao.findTop10ByOrderByLikeNumDesc();
		} else {
			documents = documentDao.findTop10ByLikeNumBeforeOrderByLikeNumDesc(likeNum);
		}
		if (!documents.isEmpty()) {
			List<Document> sameLikeNumDocuments = documentDao.findDocumentsByLikeNum(documents.get(documents.size()-1).getLikeNum());
			addSame(documents, sameLikeNumDocuments);
		}
		return documents;
	}

	/**
	 * 把与10条数据中最后一条数据时间戳或者热度相同的数据添加到这10条数据中去
	 * @param documents 10条数据
	 * @param sameDocuments 与最后一条数据时间戳或者热度相同的数据
	 */
	private void addSame(List<Document> documents, List<Document> sameDocuments){
		for(Document sameDocument : sameDocuments) {
			boolean flag = false;
			for(Document document : documents){
				if(sameDocument.getId().equals(document.getId())){
					flag = true;
					break;
				}
			}
			if(!flag){
				documents.add(sameDocument);//将时间戳或者热度相同的数据，除掉10条数据中已经有的那条
			}
		}
	}
}
