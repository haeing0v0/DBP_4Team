package HRM.Manage.service;

import HRM.Manage.domain.Pay;
import HRM.Manage.repository.PayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PayService {
    private final PayRepository payRepository;

    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    public Optional<Pay> findPayOne(Integer id) {return payRepository.findPayById(id);}
    public boolean saveIncen(Integer id, int incen) {return payRepository.saveIncentive(id, incen);}
}
