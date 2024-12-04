package HRM.Manage.service;

import HRM.Manage.domain.Commute;
import HRM.Manage.repository.CommuteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CommuteService {
    private final CommuteRepository commuteRepository;
    public CommuteService(CommuteRepository commuteRepository){this.commuteRepository = commuteRepository;}

    public Optional<Commute> findCommuteById(Integer id) {
        return commuteRepository.findById(id);
    }
}
