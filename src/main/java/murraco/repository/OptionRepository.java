package murraco.repository;

import murraco.model.Options;
import murraco.model.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Options, Integer> { }
