package HRM.Manage.repository;

import HRM.Manage.domain.Commute;

import java.util.List;
import java.util.Optional;

public interface CommuteRepository {

    Commute save(Commute commute);
    List<Commute> findById(Integer id);
}
