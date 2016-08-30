package org.brightworks.friflow.controller.quotation;

import org.brightworks.friflow.domain.dto.QuotationDTO;
import org.brightworks.friflow.exceptions.FormDataIntegrityException;
import org.brightworks.friflow.service.quotation.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @auhtor kdavid
 */
@Deprecated
@Controller
@RequestMapping("/quotation")
@SessionAttributes("quotation")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @RequestMapping("/new")
    public ModelAndView newQuotation(){
        ModelAndView mav = new ModelAndView("quotation/quotation_form");
        mav.addObject("quotation",new QuotationDTO());
        return mav;
    }

    @RequestMapping("/view/{id}")
    public ModelAndView viewQuotation(@PathVariable("id") Long id)  {
        ModelAndView mav = new ModelAndView("quotation/quotation_view");
        mav.addObject("quotation",quotationService.findOne(id));
        return mav;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editQuotation(@PathVariable("id") Long id)  {
        ModelAndView mav = new ModelAndView("quotation/quotation_form");
        mav.addObject("quotation",quotationService.findOne(id));
        return mav;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("quotation") QuotationDTO quotationDTO,
                             @RequestParam("file")List<MultipartFile> file)  {
        QuotationDTO quotation = null;
        try {
            quotation = quotationService.save(quotationDTO,file);
        } catch (FormDataIntegrityException e) {
            ModelAndView mav = new ModelAndView("quotation/quotation_form");
            mav.addObject(quotationService.findOne(quotationDTO.getId()));
            mav.addObject("error","Ticket has been recently updated, Updating the form with recent data");
            return mav;
        }
        return new ModelAndView("redirect:/quotation/view/"+quotation.getId());
    }

    @RequestMapping("/search")
    public ModelAndView displaySearch(Pageable pageable){
        return new ModelAndView("quotation/quotation_list");
    }

}

