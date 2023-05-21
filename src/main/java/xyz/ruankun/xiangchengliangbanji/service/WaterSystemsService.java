package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSystems;
import xyz.ruankun.xiangchengliangbanji.entity.WaterType;
import xyz.ruankun.xiangchengliangbanji.repository.WaterSystemsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WaterSystemsService {
    @Autowired
    private WaterSystemsRepository waterSystemsRepository;

    public List<WaterSystems> getAllWaterSystems() {
        return waterSystemsRepository.findAll();
    }

    public Optional<WaterSystems> getWaterSystemsById(Integer id) {
        return waterSystemsRepository.findById(id);
    }

    public List<WaterSystems> getWaterSystemsByRiverCodeOrRiverName(String riverCode, String riverName) {
        return waterSystemsRepository.findByRiverCodeOrRiverName(riverCode, riverName);
    }

    public List<WaterSystems> getWaterSystemsByWaterType(String waterType) {
        return waterSystemsRepository.findByWaterType(WaterType.valueOf(waterType));
    }
}

