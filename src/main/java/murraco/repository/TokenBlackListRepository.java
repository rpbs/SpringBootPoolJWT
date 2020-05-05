package murraco.repository;

import murraco.model.Awnsers;
import murraco.model.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Integer> { }
