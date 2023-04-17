package com.ipi.jva320;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HomeController {
    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping(value = "/")
    private String home(final ModelMap modelMap) throws Exception{

        modelMap.put("salarieCount", salarieAideADomicileService.countSalaries());

        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile("Jean",
                LocalDate.now(), LocalDate.now(), 10, 0,
                80, 5, 1);
        salarieAideADomicileService.creerSalarieAideADomicile(salarieAideADomicile);

        return "home";
    }
}
