package HRM.Manage.controller;

import HRM.Manage.domain.CompanyPosition;
import HRM.Manage.domain.Department;
import HRM.Manage.service.CompanyPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CompanyPositionController {
    private final CompanyPositionService companyPositionService;

    @Autowired
    public CompanyPositionController(CompanyPositionService companyPositionService) {this.companyPositionService =companyPositionService;}

    @GetMapping("/companyposition")
    public String companyPoistionList(Model model) {
        List<CompanyPosition> companyPositions = companyPositionService.findAllCompanyPosition();
        // 로그로 확인
        if (companyPositions == null || companyPositions.isEmpty()) {
            System.out.println("직위 정보가 비어있습니다.");
        } else {
            System.out.println("직위 정보가 존재합니다.");
        }
        model.addAttribute("companyPosition", companyPositions);
        return "companyPosition/companyPositionList";
    }

}
