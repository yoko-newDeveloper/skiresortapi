package com.example.skiresortapi.repository;

import com.example.skiresortapi.entity.Skiresort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * スキーリポジトリインターフェース
 */
@Repository
public interface SkiresortRepository extends JpaRepository<Skiresort, Long> {
    
}
