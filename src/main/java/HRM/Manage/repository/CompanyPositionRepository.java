package HRM.Manage.repository;

import HRM.Manage.domain.CompanyPosition;

import java.util.List;

public interface CompanyPositionRepository {

    List<CompanyPosition> findAllCompany_position();
}
