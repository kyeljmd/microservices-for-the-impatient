package org.brightworks.friflow.controller.production;

import org.brightworks.friflow.domain.dto.ProductionDTO;
import org.brightworks.friflow.domain.dto.QuotationDTO;
import org.brightworks.friflow.exceptions.FormDataIntegrityException;
import org.brightworks.friflow.service.quotation.QuotationService;
import org.brightworks.friflow.service.TicketGeneratorService;
import org.brightworks.friflow.service.production.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by ming on 10/17/15.
 */
@Deprecated
@Controller
@RequestMapping("/production")
public class ProductionController {

    @Autowired
    private ProductionService productionService;

    @Autowired
    private QuotationService quotationService;

    @Autowired
    private TicketGeneratorService ticketGeneratorService;

    @RequestMapping("/new")
    public ModelAndView newQuotation(){
        ModelAndView mav = new ModelAndView("production/production_form");
        ProductionDTO productionDTO = new ProductionDTO();
        productionDTO.setPurchaseNumber(ticketGeneratorService.ticketNumber(productionDTO));
        mav.addObject("production", productionDTO);
        return mav;
    }

    @RequestMapping("/new/{ticketNumber}")
    public ModelAndView newProduction(@PathVariable("ticketNumber") String ticketNumber)  {
        ModelAndView mav = new ModelAndView("production/production_form");
        QuotationDTO quotationDTO = quotationService.findOne(ticketNumber);
        ProductionDTO productionDTO = new ProductionDTO();
        productionDTO.setCustomerName(quotationDTO.getCustomerName());
        productionDTO.setPurchaseNumber(quotationDTO.getTicketNumber());
        mav.addObject("production", productionDTO);
        mav.addObject("from_quotation",true);
        return mav;
    }

    @RequestMapping("/view/{id}")
    public ModelAndView viewProduction(@PathVariable("id") Long id)  {
        ModelAndView mav = new ModelAndView("production/production_view");
        mav.addObject("production", productionService.findOne(id));
        return mav;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editProduction(@PathVariable("id") Long id)  {
        ModelAndView mav = new ModelAndView("production/production_form");
        mav.addObject("production", productionService.findOne(id));
        return mav;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute ProductionDTO productionDTO, @RequestParam("file")List<MultipartFile> file){
        ProductionDTO production = null;
        try {
            production = productionService.save(productionDTO,file);
        } catch (FormDataIntegrityException e) {
            ModelAndView mav = new ModelAndView("production/production_form");
            mav.addObject(productionService.findOne(productionDTO.getId()));
            mav.addObject("error", "Ticket has been recently updated, Updating the form with recent data");
            return mav;
        }
        return new ModelAndView("redirect:/production/view/"+production.getId());
    }

    @RequestMapping("/search")
    public ModelAndView displaySearch(Pageable pageable){
        return new ModelAndView("production/production_list");
    }

}
