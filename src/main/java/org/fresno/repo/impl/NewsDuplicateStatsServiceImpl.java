package org.fresno.repo.impl;

import lombok.RequiredArgsConstructor;
import org.fresno.domain.NewsDuplicateStats;
import org.fresno.repo.NewsDuplicateStatsRepository;
import org.fresno.repo.NewsDuplicateStatsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsDuplicateStatsServiceImpl implements NewsDuplicateStatsService {
    private final NewsDuplicateStatsRepository newsDuplicateStatsRepository;

    @Override
    public List<NewsDuplicateStats> get() {
        return newsDuplicateStatsRepository.findAll();

    }
}
