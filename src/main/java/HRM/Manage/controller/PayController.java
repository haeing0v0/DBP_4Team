package HRM.Manage.controller;

import HRM.Manage.domain.Pay;
import HRM.Manage.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PayController {
    private final PayService payService;

    @Autowired
    public PayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/pay/one")
    public String payOne(@RequestParam(value = "id", required = false) Integer id, Model model) {
        System.out.println(id);
        if(id == null) {
            return "pay/payOne";
        }
        List<Pay> pay = payService.findPayOne(id);
        model.addAttribute("payOne", pay);
        return "pay/payOneResult";
    }

    @GetMapping("/pay/enter")
    public String enterPay() {
        return "pay/enterIncentive";
    }

    @PostMapping("/pay/save")
    public String save(@RequestParam(value = "id") Integer id, @RequestParam(value = "incentive") Integer incentive, Model model) {
        boolean isSuccess = payService.saveIncen(id, incentive);
        // 성공 여부에 따라 다른 뷰로 이동
        if (isSuccess) {
            model.addAttribute("message", "인센티브가 성공적으로 업데이트되었습니다.");
        } else {
            model.addAttribute("message", "인센티브 업데이트에 실패했습니다.");
        }
        return "pay/enterIncentiveResult";

    }

}
