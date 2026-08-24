package com.sa2.repository;

import com.sa2.model.Lanche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancheRepository extends JpaRepository<Lanche, Long> {
    Page<Lanche> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
