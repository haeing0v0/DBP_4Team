package HRM.Manage.controller;

import HRM.Manage.domain.Commute;
import HRM.Manage.service.CommuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CommuteController {
    private final CommuteService commuteService;

    @Autowired
    public CommuteController(CommuteService commuteService){this.commuteService = commuteService;}

    @GetMapping("/commute/find")
    public String findbyid(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Optional<Commute> commuteOptional = commuteService.findCommuteById(id);
            if (commuteOptional.isPresent()) {
                model.addAttribute("commute", commuteOptional.get());
            } else {
                model.addAttribute("message", "Commute 정보가 없습니다.");
            }
            model.addAttribute("commuteId", id);
        } else {
            model.addAttribute("message", "유효한 ID를 입력해 주세요.");
        }
        return "/commute/commuteOne";
    }
}
