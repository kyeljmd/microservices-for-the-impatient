package org.brightworks.friflow.repo.quotation.impl;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import org.brightworks.friflow.domain.dto.QuotationDTO;
import org.brightworks.friflow.domain.process.ProcessStatus;
import org.brightworks.friflow.domain.process.quotation.QQuotation;
import org.brightworks.friflow.domain.process.quotation.Quotation;
import org.brightworks.friflow.mapper.OrikaBeanMapper;
import org.brightworks.friflow.repo.quotation.QuotationRepoCustom;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kyel
 */
public class QuotationRepoImpl implements QuotationRepoCustom {

    @Autowired
    private EntityManager em;

    @Autowired
    private OrikaBeanMapper quotationConverter;

    @Override
    public Page<QuotationDTO> search(String ticketNumber,
                                     String description,
                                     String clientName,
                                     LocalDate startDate,
                                     LocalDate endDate,
                                     ProcessStatus status,
                                     Pageable pageable) {
        JPAQuery query = new JPAQuery(em);
        QQuotation q = QQuotation.quotation;
        BooleanBuilder expression = null;
        expression = expressionBuilder(ticketNumber, description, clientName, startDate, endDate,status, q);

        List<Quotation> results = queryBuilder(pageable, query, q, expression);
        List<QuotationDTO> res = new ArrayList<>();
        for(Quotation quotation : results){
            res.add(quotationConverter.map(quotation, QuotationDTO.class));
        }

        if(pageable != null){
            return new PageImpl<>(res,pageable,query.count());
        }else{
            return new PageImpl<QuotationDTO>(res);
        }
    }


    private BooleanBuilder expressionBuilder(String ticketNumber,
                                             String description,
                                             String clientName,
                                             LocalDate startDate,
                                             LocalDate endDate,ProcessStatus status, QQuotation quotation) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(ticketNumber != null && !ticketNumber.isEmpty()){
            booleanBuilder.or(quotation.ticketNumber.contains(ticketNumber));
        }

        if(description != null && !description.isEmpty()){
            booleanBuilder.or(quotation.itemDescription.contains(description));
        }

        if(clientName !=null && !clientName.isEmpty()){
            booleanBuilder.or(quotation.clientName.name.contains(clientName));
        }

        //Append date if start date and end date is not equal to null
        if(startDate != null  && endDate != null){
            booleanBuilder.and(quotation.targetDate.goe(startDate).and(quotation.targetDate.loe(endDate)));
        }



        if(status != null) {
            booleanBuilder.and(quotation.processStatus.eq(status));
        }

        booleanBuilder.and(quotation.deleted.eq(false));
        return booleanBuilder;
    }


    private List<Quotation> queryBuilder(Pageable pageable, JPAQuery query, QQuotation quotation,
                                            BooleanBuilder expression) {

        if(pageable != null){
            return query.from(quotation)
                    .where(expression)
                    .limit(pageable.getPageSize())
                    .offset(pageable.getOffset())
                    .orderBy(quotation.targetDate.asc())
                    .list(quotation);
        }else{
            return query.from(quotation)
                    .where(expression)
                    .orderBy(quotation.dateTimeCreated.asc())
                    .list(quotation);
        }

    }

    //DO NOT REMOVE JUST IN CASE THE CLIENT CHANGE HIS MIND
/*
    private BooleanExpression expressionBuilder(String ticketNumber, String description, LocalDate startDate,
                                                LocalDate endDate, QQuotation quotation,
                                                BooleanExpression expression) {
        //Searching for ticket only
        if(!ticketNumber.isEmpty() && description.isEmpty()){
            expression = quotation.ticketNumber.eq(ticketNumber);
            if(startDate != null && endDate != null){
                expression.and(quotation.targetDate.between(startDate, endDate));
            }
        }

        //Searching for description only
        if(ticketNumber.isEmpty() && !description.isEmpty() ){
            expression = quotation.itemDescription.contains(description);
            if(startDate != null && endDate != null){
                expression.and(quotation.targetDate.between(startDate,endDate));
            }
        }

        //Search for description and ticket number
        if(!ticketNumber.isEmpty() && !description.isEmpty() ){
            expression = quotation.ticketNumber.eq(ticketNumber).and(quotation.itemDescription.contains(description));
            if(startDate != null && endDate != null){
                expression.and(quotation.targetDate.between(startDate,endDate));
            }
        }

        if(ticketNumber.isEmpty() && description.isEmpty() && startDate != null && endDate != null){
            expression = quotation.targetDate.goe(startDate).and(quotation.targetDate.loe(endDate));
        }

        return expression;
    }*/
}
