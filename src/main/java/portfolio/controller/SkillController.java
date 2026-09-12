package portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.model.Skill;
import portfolio.service.ISkillService;

import java.util.List;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final ISkillService skillService;

    @GetMapping
    public String listSkills(Model model){

        List<Skill> skills = skillService.findAll();
        model.addAttribute("skills",skills);

        return "skills/list-skills";
    }

}
