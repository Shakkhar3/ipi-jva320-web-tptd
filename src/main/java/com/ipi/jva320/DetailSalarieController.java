package com.ipi.jva320;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class DetailSalarieController {
    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping(value = "/salaries/{id}")
    private String detailSalarie(final ModelMap modelMap, @PathVariable Long id) throws Exception{
        modelMap.put("salarie", salarieAideADomicileService.getSalarie(id));

        SalarieAideADomicile aide = new SalarieAideADomicile("Jeannette Dupontelle",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 10,
                1, 0);
        salarieAideADomicileService.creerSalarieAideADomicile(aide);

        return "detail_Salarie";
    }

    @GetMapping(value = "/salaries/aide/new")
    private String aideSalaries(final ModelMap modelMap) throws  Exception {
        return "detail_Salarie";
    }

    /**
     * création
     * @param salarie
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/salaries/save")
    private String saveSalaries(SalarieAideADomicile salarie) throws  Exception {
        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        return "detail_Salarie";
    }

    @PostMapping(value = "/salaries/aide/{id}")
    private String updateSalaries(SalarieAideADomicile salarie) throws  Exception {
        salarieAideADomicileService.updateSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }

    @GetMapping(value = "/salaries")
    private String listSalarie(final ModelMap modelMap) throws Exception{
        modelMap.put("salaries", salarieAideADomicileService.getSalaries());
        return "list";
    }

    @GetMapping(value = "/salaries/delete/{id}")
    private String deleteSalarie(SalarieAideADomicile salarie, @PathVariable Long id) throws Exception{
        salarieAideADomicileService.deleteSalarieAideADomicile(salarie.getId());
        return "list";
    }

    @GetMapping(value = "/salaries/recherche")
    private String rechercheParNom(@RequestParam("nom") String nom, final ModelMap modelMap) throws Exception{
        if (salarieAideADomicileService.getSalaries(nom)!= null) {
            modelMap.put("salaries", salarieAideADomicileService.getSalaries(nom));
        }
        return "list";
    }
}
