package murraco.repository;

import murraco.dto.PoolDTO;
import murraco.model.Pool;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoolRepository extends JpaRepository<Pool, Integer> {

    Pool findByIdEquals(Integer Id);

}