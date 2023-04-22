package org.fresno.repo;

import org.fresno.domain.NewsDuplicateStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDuplicateStatsRepository extends JpaRepository<NewsDuplicateStats, Long> {
}