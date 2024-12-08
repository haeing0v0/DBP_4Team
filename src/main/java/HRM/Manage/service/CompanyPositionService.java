package HRM.Manage.service;


import java.util.List;

import org.springframework.stereotype.Service;

import HRM.Manage.domain.CompanyPosition;
import HRM.Manage.repository.CompanyPositionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompanyPositionService {
    private final CompanyPositionRepository companyPositionRepository;
    public CompanyPositionService(CompanyPositionRepository companyPositionRepository) {this.companyPositionRepository = companyPositionRepository;}

    public List<CompanyPosition> findAllCompanyPosition(){
        return companyPositionRepository.findAllCompany_position();
    }

}
