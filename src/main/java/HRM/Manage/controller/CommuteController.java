package HRM.Manage.controller;

import HRM.Manage.domain.Commute;
import HRM.Manage.service.CommuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commute")
public class CommuteController {
    private final CommuteService commuteService;

    @Autowired
    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    @GetMapping("/find")
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("직원 ID를 입력해주세요.");
        }

        List<Commute> commuteOne = commuteService.findCommuteById(id);

        if (commuteOne.isEmpty()) {
            return ResponseEntity.badRequest().body("해당 ID의 직원이 존재하지 않습니다.");
        }

        System.out.println("Commute Data: " + commuteOne);

        return ResponseEntity.ok(commuteOne);
    }

    // 출퇴근 기록 저장
    @PostMapping("/enter")
    public ResponseEntity<?> enter(@RequestBody Commute form) {
        Commute commute = new Commute();
        commute.setEmployee_id(form.getEmployee_id());
        commute.setStartWorkTime(form.getStartWorkTime());
        commute.setFinishWorkTime(form.getFinishWorkTime());
        commute.setWorkDay(form.getWorkDay());
        commuteService.save(commute);

        return ResponseEntity.ok("출퇴근 기록이 성공적으로 저장되었습니다.");
    }
}
