package org.fresno.repo;

import org.fresno.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Transactional
    @Modifying
    @Query("update News n set n.isPublished = ?1 where n.id = ?2")
    int updateIsPublishedById(boolean isPublished, Long id);

    List<News> findByIsPublished(boolean isPublished);
    List<News> findByTitle(String title);

}