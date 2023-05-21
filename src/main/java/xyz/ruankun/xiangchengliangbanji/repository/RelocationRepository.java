package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.Relocation;

@Repository
public interface RelocationRepository extends JpaRepository<Relocation, Integer> {
}

