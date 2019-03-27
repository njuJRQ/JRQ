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
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public InfoResponse addDocument(String title, String content, String image, String attachment, String writerName, int price, long likeNum, boolean isContract) {
        String preview = generatePreviewImage(attachment);
        documentDataService.addDocument(new Document(title, content, image, attachment, writerName, price, System.currentTimeMillis(), likeNum, preview, isContract));
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
        for (Document document : documents) {
            documentItems.add(new DocumentItem(document));
        }
        return new DocumentListResponse(documentItems);
    }

    @Override
    public DocumentListResponse getContractList() {
        List<Document> documents = documentDataService.getAllContracts();
        List<DocumentItem> documentItems = new ArrayList<>();
        for (Document document : documents) {
            documentItems.add(new DocumentItem(document));
        }
        return new DocumentListResponse(documentItems);
    }

    @Override
    public InfoResponse updateDocument(String id, String title, String content, String image, String attachment, String writerName, int price, long likeNum) throws NotExistException {
        Document document = documentDataService.getDocumentById(id);
        document.setTitle(title);
        document.setContent(content);
        document.setImage(image);
        document.setAttachment(attachment);
        document.setWriterName(writerName);
        document.setPrice(price);
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
    public DocumentListResponse getMyDocumentList(String openid, boolean isContract) {
        List<Document> documents = new ArrayList<>();
        if (isContract) {
            documents = documentDataService.getAllContracts();
        }else{
            documents=documentDataService.getAllDocuments();
        }
        List<DocumentItem> documentItems = new ArrayList<>();
        for (Document document : documents) {
            boolean hasLiked = likeDataService.isLikeExistent(openid, "document", document.getId());
            documentItems.add(new DocumentItem(document, hasLiked));
        }
        return new DocumentListResponse(documentItems);
    }

    @Override
    public DocumentListResponse getMyDocumentListBefore(String openid, String id,boolean isContract) throws NotExistException {
        List<Document> documents = documentDataService.getMyDocumentListBefore(openid, id,isContract);
        List<DocumentItem> documentItems = new ArrayList<>();
        for (Document document : documents) {
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
            BufferedImage image = (BufferedImage) pdfDoc.getPageImage(0, GraphicsRenderingHints.SCREEN,
                    Page.BOUNDARY_CROPBOX, rotation, zoom);
            Iterator iter = ImageIO.getImageWritersBySuffix("jpg");
            ImageWriter writer = (ImageWriter) iter.next();

            String previewImage = attachment + "-preview.jpg";
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

    //定时任务：每天14:25点自动为没有附件预览图的文档生成预览图（用于为以前在数据库中的文档附件生成预览图）

    //	@Scheduled(cron = "0 25 14 * * ?")
    private void genPreviews() {
        List<Document> documents = documentDataService.getAllDocuments();
        for (Document document : documents) {
            if ((!document.getAttachment().equals("")) && document.getPreview() == null) {
                //若此文档有附件，但是预览图为空，则为它生成预览图
                document.setPreview(generatePreviewImage(document.getAttachment()));
                documentDataService.saveDocument(document);
            }
        }
    }
}
