package HRM.Manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HRM.Manage.domain.Pay;
import HRM.Manage.service.PayService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pay")
public class PayController {
    private final PayService payService;

    @Autowired
    public PayController(PayService payService) {
        this.payService = payService;
    }

    // 급여 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<?> getPay(@PathVariable(name = "id") Integer employeeId) {
        try {
            List<Pay> pay = payService.findPayOne(employeeId);

            if (pay == null || pay.isEmpty()) {
                return ResponseEntity.status(404).body("해당 ID의 급여 정보가 존재하지 않습니다.");
            }

            return ResponseEntity.ok(pay);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("서버에서 오류가 발생했습니다.");
        }
    }

    // 인센티브 저장 API
    @PostMapping("/save")
    public ResponseEntity<?> savePay(
            @RequestParam(name = "id") Integer employeeId,
            @RequestParam(name = "incentive") Integer incentiveAmount) {

        try {
            boolean isSuccess = payService.saveIncen(employeeId, incentiveAmount);
            System.out.println("Updating EMPLOYEE_ID: " + employeeId + " with incentive: " + incentiveAmount);

            if (isSuccess) {
                return ResponseEntity.ok("인센티브가 성공적으로 업데이트되었습니다.");
            } else {
                return ResponseEntity.status(400).body("인센티브 업데이트에 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("서버에서 오류가 발생했습니다.");
        }
    }
}
