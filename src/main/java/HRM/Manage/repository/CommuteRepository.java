package HRM.Manage.repository;

import HRM.Manage.domain.Commute;

import java.util.Optional;

public interface CommuteRepository {

    Optional<Commute> findById(Integer id);
}
