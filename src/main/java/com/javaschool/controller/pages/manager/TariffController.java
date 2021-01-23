package com.javaschool.controller.pages.manager;

import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.service.OptionService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/managers")
public class TariffController {
    private final TariffService tariffService;
    private final OptionService optionService;

    @Autowired
    public TariffController(TariffService tariffService, OptionService optionService) {
        this.tariffService = tariffService;
        this.optionService = optionService;
    }
    
    @RequestMapping("/tariff")
    public ModelAndView home() {
        List<TariffDto> listTariff = tariffService.getAll();
        ModelAndView mav = new ModelAndView("jsp/tariffs/tariffList");
        mav.addObject("listTariff", listTariff);
        return mav;
    }

    @RequestMapping(value = "tariffs/update", method = RequestMethod.POST)
    public String updateOption(@RequestParam("option.id") long id,@ModelAttribute("tariff") TariffDto tariff) {
        OptionDto optionDto = optionService.getById(id);
        System.out.println(tariff);
        System.out.println(tariff.getOptions());
        if(null==tariff.getOptions()){
            Set<OptionDto> set = new HashSet<>();
            set.add(optionDto);
            tariff.setOptions(set);
        }
        else {
            tariff.getOptions().add(optionDto);
        }
        tariffService.update(tariff);
        return "redirect:/managers/tariff";
    }

    @RequestMapping("tariffs/edit")
    public ModelAndView editOption(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/tariffs/edit_tariff");
        TariffDto tariffDto = tariffService.getById(id);
        List<OptionDto> options = optionService.getAll();
        mav.addObject("tariff", tariffDto);
        mav.addObject("options", options);
        return mav;
    }

    @RequestMapping("tariffs/new")
    public String newTariff(Map<String, Object> model) {
        TariffDto tariff = new TariffDto();
        List<OptionDto> options = optionService.getAll();
        model.put("tariff", tariff);
        model.put("options", options);
        return "jsp/tariffs/new_tariff";
    }

    @RequestMapping(value = "tariffs/save", method = RequestMethod.POST)
    public String saveTariff(@RequestParam("option.id") long id, @ModelAttribute("tariff") TariffDto tariff) {
        OptionDto optionDto = optionService.getById(id);
        if(null==tariff.getOptions()){
            Set<OptionDto> set = new HashSet<>();
            set.add(optionDto);
            tariff.setOptions(set);
        }
        else {
            tariff.getOptions().add(optionDto);
        }
        tariffService.add(tariff);
        return "redirect:/managers/tariff";
    }

    @RequestMapping("/tariff/editOptions")
    public ModelAndView editMandatoryOptions(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/tariffs/addOptions");
        mav.addObject("tariff", tariffService.getById(id));
        mav.addObject("options", optionService.getAll());
        return mav;
    }

    @RequestMapping(value = "/tariff/updateOptions", method = RequestMethod.POST)
    public String updateMandatoryOption(@RequestParam("option.id") Long optionId,
                                        @RequestParam("tariff.id") Long tariffId) {
        Set<OptionDto> optionDtos = new HashSet<>();
        TariffDto tariff = tariffService.getById(tariffId);
        if(!Objects.isNull(tariff.getOptions())) {
             optionDtos = tariff.getOptions();

        }
        optionDtos.add(optionService.getById(optionId));
        tariff.setOptions(optionDtos);
        tariffService.update(tariff);
        return "redirect:/managers/tariff";
    }

    @RequestMapping(value ="tariffs/delete")
    public String deleteTariffById(@RequestParam long id) {
        tariffService.delete(id);
        return "redirect:/managers/tariff";
    }
}
