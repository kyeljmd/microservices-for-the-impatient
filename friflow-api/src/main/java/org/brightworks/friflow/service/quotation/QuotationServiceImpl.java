package org.brightworks.friflow.service.quotation;

import org.brightworks.friflow.domain.dto.QuotationDTO;
import org.brightworks.friflow.domain.process.ProcessStatus;
import org.brightworks.friflow.domain.process.attachment.Attachment;
import org.brightworks.friflow.domain.process.attachment.AttachmentMetaData;
import org.brightworks.friflow.domain.process.quotation.Quotation;
import org.brightworks.friflow.mapper.OrikaBeanMapper;
import org.brightworks.friflow.repo.attachment.AttachmentMetaDataRepo;
import org.brightworks.friflow.repo.attachment.AttachmentRepo;
import org.brightworks.friflow.repo.quotation.QuotationRepo;
import org.brightworks.friflow.exceptions.FormDataIntegrityException;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kyel
 */
@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

    private static final Logger LOG = LoggerFactory.getLogger(QuotationServiceImpl.class);

    /**
     * Part of the business rule
     * Add this field on target date
     */
    private static final int CUTOFF_DAYS = 3;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private AttachmentMetaDataRepo attachmentMetaDataRepo;

    @Autowired
    private OrikaBeanMapper mapper;

    @Override
    public QuotationDTO save(QuotationDTO quotationDTO,List<MultipartFile> attachments) throws FormDataIntegrityException {
        if(quotationDTO.getId() != null){
            Quotation quotation = quotationRepo.findOne(quotationDTO.getId());
            Quotation updatedQuotation;
            if(quotationDTO.getVersion() != quotation.getVersion()){
                LOG.error("Quotation Form Does not Match the transaction version/ID from the existing data");
                throw  new FormDataIntegrityException();
            }else{
                mapper.map(quotationDTO, quotation);
                updatedQuotation = quotation;
            }

            if(attachments != null && !attachments.isEmpty()){
                List<AttachmentMetaData> attachmentMetaDatas =    buildAttachments(attachments);
                if(updatedQuotation.getAttachments() == null){
                    updatedQuotation.setAttachments(attachmentMetaDatas);
                }else {
                    updatedQuotation.getAttachments().addAll(attachmentMetaDatas);
                }
            }

            LOG.info("Saving Quotation = {}",updatedQuotation);

            return mapper.map(quotationRepo.save(updatedQuotation), QuotationDTO.class);
        }else {
            List<AttachmentMetaData> quotationAttachments = buildAttachments(attachments);
            Quotation quotation = new Quotation();
            mapper.map(quotationDTO, quotation);
            quotation.setAttachments(quotationAttachments);
            quotation.setTargetDate(quotation.getTargetDate().plusDays(CUTOFF_DAYS));
            LOG.info("Saving Quotation = {}",quotation);
            return mapper.map(quotationRepo.save(quotation),QuotationDTO.class);
        }
    }

    private List<AttachmentMetaData> buildAttachments(List<MultipartFile> attachments) {
        LOG.info("Processing attachments included the quotation form");
        List<AttachmentMetaData> quotationAttachments = new ArrayList<>();

        if(attachments == null) {
            return quotationAttachments ;
        }

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
        return quotationAttachments;
    }

    @Override
    public QuotationDTO findOne(Long id)  {
        Quotation quotation = quotationRepo.findOne(id);
        if(quotation != null ){
            LOG.info("Found Quotation with ID={}",id);
            return  mapper.map(quotation,QuotationDTO.class);
        }else{
            LOG.info("No Quotation Found with Quotation Id={}",id);
            return null;
        }
    }

    @Override
    public QuotationDTO findOne(String ticketNumber) {
        LOG.info("Retrieving Quotation with the ticketNumber = {}",ticketNumber);

        Quotation quotation = quotationRepo.findByTicketNumber(ticketNumber);
        return mapper.map(quotation, QuotationDTO.class);
    }

    @Override
    public Page<QuotationDTO> search(String ticketNumber,
                                     String description,
                                     String clientName,
                                     LocalDate startDate,
                                     LocalDate endDate,
                                     ProcessStatus status,
                                     Pageable pageable) {

        LOG.info("Searching for Quotation with the ff parameters:\n" +
                        "ticket number={}\n" +
                        "description={}\n" +
                        "clientName={}\n" +
                        "pageNumber={}\n",
                ticketNumber,
                description,
                clientName
                );

        Page<QuotationDTO> quotationDTOs =
                quotationRepo.search(
                        ticketNumber,
                        description,
                        clientName,
                        startDate,
                        endDate,
                        status,
                        pageable);
        return quotationDTOs;
    }
}
