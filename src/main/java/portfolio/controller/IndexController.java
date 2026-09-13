package portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.service.*;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IPersonalInfoService personalInfoService;
    private final ISkillService skillService;
    private final IEducationService educationService;
    private final IExperienceService experienceService;
    private final IProjectService projectService;

    @GetMapping("/")
    public String showIndex(Model model){
        System.out.println("Mostrando la página de inicio");
        model.addAttribute("personalInfo", personalInfoService.findAll().getFirst());
        model.addAttribute("experienceList", experienceService.findAll());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("educationList", educationService.findAll());
        model.addAttribute("projectsList", projectService.findAll());
        return "index";
    }



}
