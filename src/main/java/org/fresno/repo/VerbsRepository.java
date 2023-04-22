package org.fresno.repo;

import org.fresno.domain.Verbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface VerbsRepository extends JpaRepository<Verbs, String> {


}