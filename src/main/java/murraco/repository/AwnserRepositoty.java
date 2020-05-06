package murraco.repository;

import murraco.model.Awnsers;
import murraco.model.Options;
import murraco.model.Pool;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwnserRepositoty extends JpaRepository<Awnsers, Integer> {
    Awnsers findByPoolAndUser(Pool p, User u);
    Awnsers findByAwnserIdEquals(Integer AwnserId);
    Boolean existsByPoolAndOption(Pool p, Options o);
    Boolean existsByPoolAndOptionAndUser(Pool p, Options o, User u);
    List<Awnsers> findByPool(Pool p);
    Integer countAwnsersByOption(Options o);
}
