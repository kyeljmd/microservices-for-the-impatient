package org.brightworks.friflow.ticketing.mapper.impl;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.brightworks.friflow.ticketing.domain.dto.AttachmentDTO;
import org.brightworks.friflow.ticketing.domain.dto.QuotationDTO;
import org.brightworks.friflow.ticketing.domain.process.ProcessStatus;
import org.brightworks.friflow.ticketing.domain.process.attachment.AttachmentMetaData;
import org.brightworks.friflow.ticketing.domain.process.quotation.Quotation;
import org.brightworks.friflow.ticketing.repo.CompanyNameRepo;
import org.brightworks.friflow.ticketing.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kyel
 */
@Component
public class QuotationMapper  extends CustomMapper<Quotation, QuotationDTO> {

    @Autowired
    private CompanyNameRepo companyNameRepo;

    public void mapAtoB(Quotation quotation, QuotationDTO quotationDTO, MappingContext context) {
        quotationDTO.setClientId(quotation.getClientId());
        quotationDTO.setPrice(quotation.getPrice().doubleValue());
        quotationDTO.setTargetDate(DateUtil.formatDate(quotation.getTargetDate()));
        quotationDTO.setStatus(quotation.getProcessStatus().toString());
        quotationDTO.setCreatedDateTime(DateUtil.formatDateTime(quotation.getDateTimeCreated(), "MM-dd-yyyy hh:mm aa"));
        List<AttachmentDTO> attachmentMetaDatas = new ArrayList<AttachmentDTO>();
        for(AttachmentMetaData attachment: quotation.getAttachments()) {
            if (!attachment.isDeleted()) {
                AttachmentDTO dto = new AttachmentDTO();
                dto.setFileName(attachment.getFileName());
                dto.setAttachmentId(attachment.getAttachment().getId().toString());
                dto.setContentType(attachment.getContentType());
                dto.setAttachmentMetaDataId(attachment.getId().toString());
                attachmentMetaDatas.add(dto);
            }
        }
        if(quotation.getProcessStatus() != null
                && (quotation.getProcessStatus()
                .equals(ProcessStatus.COMPLETED))) {
            quotationDTO.setEditable(false);
        }
        quotationDTO.setAttachments(!attachmentMetaDatas.isEmpty() ? attachmentMetaDatas : null );
    }

    public void mapBtoA(QuotationDTO dto, Quotation quotation, MappingContext context) {
        quotation.setClientId(dto.getClientId());
        quotation.setItemDescription(dto.getItemDescription());
        quotation.setPrice(new BigDecimal(dto.getPrice() != null ? dto.getPrice() : 0));
        quotation.setTargetDate(DateUtil.formatDate(dto.getTargetDate()));
        quotation.setProcessStatus(ProcessStatus.valueOf(dto.getStatus()));
    }

    private boolean doesCompanyNameExist(String companyName){
        return (companyNameRepo.findByName(companyName.toUpperCase()) != null);
    }

}
