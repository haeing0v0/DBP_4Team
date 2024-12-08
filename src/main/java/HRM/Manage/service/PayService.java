package HRM.Manage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import HRM.Manage.domain.Pay;
import HRM.Manage.repository.PayRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PayService {
    private final PayRepository payRepository;

    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    public List<Pay> findPayOne(Integer id) {return payRepository.findPayById(id);}
    public boolean saveIncen(Integer id, int incen) {return payRepository.saveIncentive(id, incen);}
}
