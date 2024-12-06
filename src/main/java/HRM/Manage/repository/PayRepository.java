package HRM.Manage.repository;

import HRM.Manage.domain.Pay;

import java.util.List;
import java.util.Optional;

public interface PayRepository {

    List<Pay> findPayById(Integer id);
    boolean saveIncentive(Integer id, int incentive);
}
