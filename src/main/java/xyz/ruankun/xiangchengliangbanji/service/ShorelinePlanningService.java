package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.ShorelinePlanning;
import xyz.ruankun.xiangchengliangbanji.repository.ShorelinePlanningRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShorelinePlanningService {

    @Autowired
    private ShorelinePlanningRepository shorelinePlanningRepository;

    public ShorelinePlanning findById(Integer id) {
        Optional<ShorelinePlanning> optional = shorelinePlanningRepository.findById(id);
        return optional.orElse(null);
    }

    public List<ShorelinePlanning> findByWaterSystemsId(Integer waterSystemsId) {
        return shorelinePlanningRepository.findByWaterSystemsId(waterSystemsId);
    }
}
