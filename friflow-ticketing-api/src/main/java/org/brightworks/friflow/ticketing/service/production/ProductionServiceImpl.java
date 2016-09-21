package org.brightworks.friflow.ticketing.service.production;

import org.brightworks.friflow.ticketing.domain.dto.AttachmentDTO;
import org.brightworks.friflow.ticketing.domain.dto.ProductionDTO;
import org.brightworks.friflow.ticketing.domain.process.ProcessStatus;
import org.brightworks.friflow.ticketing.domain.process.attachment.Attachment;
import org.brightworks.friflow.ticketing.domain.process.attachment.AttachmentMetaData;
import org.brightworks.friflow.ticketing.domain.process.production.Production;
import org.brightworks.friflow.ticketing.domain.process.quotation.Quotation;
import org.brightworks.friflow.ticketing.exceptions.FormDataIntegrityException;
import org.brightworks.friflow.ticketing.mapper.OrikaBeanMapper;
import org.brightworks.friflow.ticketing.repo.attachment.AttachmentMetaDataRepo;
import org.brightworks.friflow.ticketing.repo.attachment.AttachmentRepo;
import org.brightworks.friflow.ticketing.repo.production.ProductionRepo;
import org.brightworks.friflow.ticketing.repo.quotation.QuotationRepo;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kdavid
 */
@Service
public class ProductionServiceImpl implements ProductionService {


    private static final Logger LOG = LoggerFactory.getLogger(ProductionServiceImpl.class);

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private ProductionRepo productionRepo;

    @Autowired
    private OrikaBeanMapper mapper;

    @Autowired
    private AttachmentMetaDataRepo attachmentMetaDataRepo;

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Override
    public ProductionDTO save(ProductionDTO productionDTO, List<MultipartFile> attachments) throws FormDataIntegrityException {
        Production production;
        LOG.info("Saving Production Ticket = {}",productionDTO.toString());
        if(productionDTO.getId() != null){
            production = productionRepo.findOne(productionDTO.getId());

            if(production.getVersion() != productionDTO.getVersion()) throw new FormDataIntegrityException();
            mapper.map(productionDTO,production);
        }else{
            production = mapper.map(productionDTO,Production.class);
        }

        if(productionDTO.getPurchaseNumber() != null){
            Quotation quotation = quotationRepo.findByTicketNumber(productionDTO.getPurchaseNumber());
            if(quotation != null) {
                production.setQuotation(quotation);
            }
            production.setPurchaseNumber(productionDTO.getPurchaseNumber());
        }

        if(!attachments.isEmpty()) {
            List<AttachmentMetaData> attachmentMetaDatas = buildAttachments(attachments);
            if (production.getAttachments() == null) {
                production.setAttachments(attachmentMetaDatas);
            } else {
                production.getAttachments().addAll(attachmentMetaDatas);
            }
        }
        productionRepo.save(production);
        return mapper.map(production,ProductionDTO.class);
    }

    @Override
    public ProductionDTO findOne(Long id) {
        LOG.info("Searching for Production Ticket with Id = {}",id);
        Production production = productionRepo.findOne(id);
        ProductionDTO productionDTO = mapper.map(production,ProductionDTO.class);
        List<AttachmentDTO> attachmentMetaDatas = new ArrayList<>();
        for(AttachmentMetaData attachment: production.getAttachments()){
            if(!attachment.isDeleted()){
                AttachmentDTO dto = new AttachmentDTO();
                dto.setFileName(attachment.getFileName());
                dto.setAttachmentId(attachment.getAttachment().getId().toString());
                dto.setContentType(attachment.getContentType());
                dto.setAttachmentMetaDataId(attachment.getId().toString());
                attachmentMetaDatas.add(dto);
            }
        }

        productionDTO.setAttachments(!attachmentMetaDatas.isEmpty() ? attachmentMetaDatas : null );
        return productionDTO;
    }

    @Override
    public ProductionDTO findOne(String ticketNumber) {
        LOG.info("Searching for Production Ticket number = {}",ticketNumber);
        return mapper.map(productionRepo.findByTicketNumber(ticketNumber),ProductionDTO.class);

    }

    @Override
    public ProductionDTO findOneByJobOrderNumber(String jobOrderNumber){
        LOG.info("Searching for Production ticket with Job order number = {}",jobOrderNumber);
        return mapper.map(productionRepo.findByQuotationTicketNumber(jobOrderNumber), ProductionDTO.class);
    }

    @Override
    public Page<ProductionDTO> search(String jobOrderNumber,
                                      String purchaseNumber,String description,Long clientId,
                                      LocalDate startDate, LocalDate endDate, ProcessStatus status,Pageable pageable) {
        LOG.info("Searching for Production tickets with ff parameters\n" +
                "jobOrderNumber={}\n" +
                "purchaseNumber={}\n" +
                "description={}\n" +
                "clientName={}",jobOrderNumber,purchaseNumber,description,clientId)
        ;
        return productionRepo.search(jobOrderNumber,
                 purchaseNumber,description,clientId,startDate,endDate,status,pageable);
    }

    private List<AttachmentMetaData> buildAttachments(List<MultipartFile> attachments) {
        LOG.info("Processing attachments included the quotation form");
        List<AttachmentMetaData> quotationAttachments = new ArrayList<>();
        for(MultipartFile file: attachments){
            if(file.getSize() > 0){
                AttachmentMetaData metaData = new AttachmentMetaData();
                metaData.setContentType(file.getContentType());
                metaData.setFileName(file.getOriginalFilename());
                Attachment attachment = new Attachment();
                try {
                    attachment.setFile(file.getBytes());
                    attachment.setAttachmentMetaData(metaData);
                    metaData.setAttachment(attachment);
                    AttachmentMetaData attachmentMetaData = attachmentMetaDataRepo.save(metaData);
                    attachmentRepo.save(attachment);
                    quotationAttachments.add(attachmentMetaData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOG.info("Successfully processed attachments");
        return quotationAttachments;
    }
}
