package com.luchnik.luchnik.repository;

import com.luchnik.luchnik.entity.PlayerSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface PlayerSetRepository extends JpaRepository<PlayerSet, Integer> {

    @Query(value = "select playerset.* from playerset where playerset.playername=?1", nativeQuery = true)
    List<PlayerSet> findPlayerSetByPlayername (@Param("playername") String playername);

    @Modifying
    @Transactional
    @Query(value = "update playerset set set0=?, set1=?, set2=? where id=? and playername=?", nativeQuery = true)
    int updatePlayerSet(@Param("set0") Integer set0,
                  @Param("set1") Integer set1,
                  @Param("set2") Integer set2,
                  @Param("id") Integer id,
                  @Param("playername") String playername);
}
