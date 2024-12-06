package HRM.Manage.controller;

import HRM.Manage.domain.CompanyPosition;
import HRM.Manage.service.CompanyPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companypositions")
public class CompanyPositionController {
    private final CompanyPositionService companyPositionService;

    @Autowired
    public CompanyPositionController(CompanyPositionService companyPositionService) {
        this.companyPositionService = companyPositionService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyPosition>> getCompanyPositions() {
        List<CompanyPosition> companyPositions = companyPositionService.findAllCompanyPosition();

        if (companyPositions == null || companyPositions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(companyPositions);
    }
}
