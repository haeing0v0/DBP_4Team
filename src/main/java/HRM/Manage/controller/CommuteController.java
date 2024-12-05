package HRM.Manage.controller;

import HRM.Manage.domain.Commute;
import HRM.Manage.domain.Employee;
import HRM.Manage.service.CommuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CommuteController {
    private final CommuteService commuteService;

    @Autowired
    public CommuteController(CommuteService commuteService){this.commuteService = commuteService;}

    @GetMapping("/commute/find")
    public String findbyid(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            // ID가 없으면 검색 화면 반환
            model.addAttribute("message", "직원 ID를 입력해주세요.");
            return "commute/commuteOne";
        }
        Optional<Commute> commuteOne = commuteService.findCommuteById(id);

        if (commuteOne.isEmpty()) {
            // 검색 결과가 없는 경우
            model.addAttribute("message", "해당 ID의 직원이 존재하지 않습니다.");
            return "commute/commuteOne";
        }
        System.out.println("Commute Data: " + commuteOne); // 디버깅 로그 추가

        // 검색 결과가 있는 경우
        model.addAttribute("commuteOne", commuteOne);
        return "commute/commuteOneResult";
    }
}
