package HRM.Manage.service;


import HRM.Manage.domain.CompanyPosition;
import HRM.Manage.repository.CompanyPositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompanyPositionService {
    CompanyPositionRepository companyPositionRepository;
    public CompanyPositionService(CompanyPositionRepository companyPositionRepository) {this.companyPositionRepository = companyPositionRepository;}

    public List<CompanyPosition> findAllCompanyPosition(){
        return companyPositionRepository.findAllCompany_position();
    }

}
