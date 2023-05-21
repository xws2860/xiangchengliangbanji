package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.RiverLakeBoundary;

@Repository
public interface RiverLakeBoundaryRepository extends JpaRepository<RiverLakeBoundary, Integer> {
}