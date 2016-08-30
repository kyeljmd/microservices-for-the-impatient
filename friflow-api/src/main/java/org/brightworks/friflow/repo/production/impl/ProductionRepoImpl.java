package org.brightworks.friflow.repo.production.impl;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import org.brightworks.friflow.domain.dto.ProductionDTO;
import org.brightworks.friflow.domain.process.ProcessStatus;
import org.brightworks.friflow.domain.process.production.Production;
import org.brightworks.friflow.domain.process.production.QProduction;
import org.brightworks.friflow.mapper.OrikaBeanMapper;
import org.brightworks.friflow.repo.production.ProductionRepoCustom;
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
public class ProductionRepoImpl implements ProductionRepoCustom{

    @Autowired
    private EntityManager em;

    @Autowired
    private OrikaBeanMapper mapper;

    @Override
    public Page<ProductionDTO> search(String jobOrderNumber,
                                      String purchaseNumber,
                                      String description,
                                      String clientName,
                                      LocalDate startDate,
                                      LocalDate endDate,
                                      ProcessStatus status,
                                      Pageable pageable) {
        JPAQuery query = new JPAQuery(em);
        QProduction q = QProduction.production;
        BooleanExpression expression = null;
        BooleanBuilder builder = expressionBuilder(jobOrderNumber,
                purchaseNumber, description, clientName,startDate, endDate,status, q);
        List<Production> results = queryBuilder(pageable, query, q, builder);
        List<ProductionDTO> res = new ArrayList<>();
        for(Production production : results){
            ProductionDTO dto = mapper.map(production, ProductionDTO.class);
            res.add(dto);
        }

        if(pageable != null){
            return new PageImpl<>(res,pageable,query.count());
        }else{
            return new PageImpl<>(res);
        }
    }


    private List<Production> queryBuilder(Pageable pageable, JPAQuery query, QProduction production,
                                          BooleanBuilder expression) {

        if(pageable != null){
            return query.from(production)
                    .where(expression)
                    .limit(pageable.getPageSize())
                    .offset(pageable.getOffset())
                    .orderBy(production.targetDate.asc())
                    .list(production);
        }else{
            return query.from(production)
                    .where(expression)
                    .orderBy(production.dateTimeCreated.asc())
                    .list(production);
        }
        
    }

    private BooleanBuilder expressionBuilder(String jobOrderNumber,
                                             String purchaseNumber,String description,
                                             String clientName,
                                             LocalDate startDate,
                                             LocalDate endDate,
                                             ProcessStatus status,
                                             QProduction production) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(jobOrderNumber != null && !jobOrderNumber.isEmpty()){
            booleanBuilder.or(production.jobOrderNumber.contains(jobOrderNumber));
        }

        if(purchaseNumber != null && !purchaseNumber.isEmpty()){
            booleanBuilder.or(production.quotation.ticketNumber.contains(purchaseNumber));
        }

        if(description != null && !description.isEmpty()){
            booleanBuilder.or(production.description.contains(description));
        }

        if(clientName !=null && !clientName.isEmpty()){
            booleanBuilder.or(production.clientName.name.contains(clientName));
        }

        //Append date if start date and end date is not equal to null
        if(startDate != null  && endDate != null){
            booleanBuilder.and(production.targetDate.goe(startDate).and(production.targetDate.loe(endDate)));
        }

        if(status != null){
            booleanBuilder.and(production.processStatus.eq(status));
        }
        
        booleanBuilder.and(production.deleted.eq(false));

        return booleanBuilder;
    }

    /*

    //Strict expression builder
    //DO NOT REMOVE JUST IN CASE THE CLIENT WANTS THIS ONE
    private BooleanBuilder expressionBuilder(String jobOrderNumber,
                                             String purchaseNumber,String description, LocalDate startDate,
                                             LocalDate endDate, QProduction production) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        //Search for job order number only
        if(!jobOrderNumber.isEmpty() && description.isEmpty()){
            booleanBuilder.and(production.jobOrderNumber.containsIgnoreCase(jobOrderNumber));
        }

        //Search for description only
        if(purchaseNumber.isEmpty() && jobOrderNumber.isEmpty() && !description.isEmpty() ){
            booleanBuilder.and(production.description
                    .containsIgnoreCase(description));

        }
        //Search for description and job order number
        if(!jobOrderNumber.isEmpty() && !description.isEmpty() ){
            booleanBuilder.and(production.jobOrderNumber.contains(jobOrderNumber)
                    .and(production.description.containsIgnoreCase(description)));

        }

        //Search for purchase Number only
        if(description.isEmpty() && jobOrderNumber.isEmpty() && !purchaseNumber.isEmpty() ){
            booleanBuilder.and(production.quotation.ticketNumber
                    .containsIgnoreCase(purchaseNumber));

        }

        //Search for purchase Number, and description
        if(!purchaseNumber.isEmpty() && !description.isEmpty() ){
            booleanBuilder.and(production.quotation.ticketNumber
                    .containsIgnoreCase(purchaseNumber)
                    .and(production.description.containsIgnoreCase(description)));
        }

        //Search for purchase number, description, and Job order number
        if(!jobOrderNumber.isEmpty() && !purchaseNumber.isEmpty() && !description.isEmpty()){
            booleanBuilder.and(production.quotation.ticketNumber
                    .containsIgnoreCase(purchaseNumber)
                    .and(production.description.containsIgnoreCase(description)).and(production.jobOrderNumber.contains(jobOrderNumber)));
        }

        //search for job number order and purchase number
        if(!jobOrderNumber.isEmpty() && !purchaseNumber.isEmpty()){
            booleanBuilder.and(production.quotation.ticketNumber
                    .containsIgnoreCase(purchaseNumber)
                   .and(production.jobOrderNumber.contains(jobOrderNumber)));
        }

        //Append date if start date and end date is not equal to null
        if(startDate != null  && endDate != null){
            booleanBuilder.and(production.targetDate.goe(startDate).and(production.targetDate.loe(endDate)));
        }

        return booleanBuilder;
    }*/
}
