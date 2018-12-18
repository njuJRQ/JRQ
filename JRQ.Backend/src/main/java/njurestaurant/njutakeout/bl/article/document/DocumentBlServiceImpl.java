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
import org.icepdf.core.pobjects.*;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
	public InfoResponse addDocument(String title, String content, String attachment, String writerName, long likeNum) {
		String preview = generatePreviewImage(attachment);
		documentDataService.addDocument(new Document(title, content, attachment, writerName, System.currentTimeMillis(), likeNum, preview));
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
	public InfoResponse updateDocument(String id, String title, String content, String attachment, String writerName, long likeNum) throws NotExistException {
		Document document = documentDataService.getDocumentById(id);
		document.setTitle(title);
		document.setContent(content);
		document.setAttachment(attachment);
		document.setWriterName(writerName);
		document.setTimeStamp(System.currentTimeMillis());
		document.setLikeNum(likeNum);
		document.setPreview(generatePreviewImage(attachment));
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

	@Override
	public DocumentListResponse getMyDocumentListBefore(String openid, String id) throws NotExistException {
		List<Document> documents = documentDataService.getMyDocumentListBefore(openid, id);
		List<DocumentItem> documentItems = new ArrayList<>();
		for(Document document:documents){
			boolean hasLiked = likeDataService.isLikeExistent(openid, "document", document.getId());
			documentItems.add(new DocumentItem(document, hasLiked));
		}
		return new DocumentListResponse(documentItems);
	}

	private String generatePreviewImage(String attachment) {
		org.icepdf.core.pobjects.Document pdfDoc = null;
		try {
			float rotation = 0f;
			//缩略图显示倍数，1表示不缩放，0.5表示缩小到50%
			float zoom = 0.8f;
			pdfDoc = new org.icepdf.core.pobjects.Document();
			pdfDoc.setFile(attachment);
			BufferedImage image = (BufferedImage)pdfDoc.getPageImage(0, GraphicsRenderingHints.SCREEN,
					Page.BOUNDARY_CROPBOX, rotation, zoom);
			Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
			ImageWriter writer = (ImageWriter)iter.next();

			String previewImage = attachment+"-preview.jpg";
			FileOutputStream out = new FileOutputStream(new File(previewImage));
			ImageOutputStream outImage = ImageIO.createImageOutputStream(out);

			writer.setOutput(outImage);
			writer.write(new IIOImage(image, null, null));
			return previewImage;
		} catch (PDFException | IOException | PDFSecurityException | InterruptedException e) {
			e.printStackTrace();
			return "";
		}
	}
}
