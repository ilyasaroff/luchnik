package com.luchnik.luchnik.repository;

import com.luchnik.luchnik.entity.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer> {
    @Query(value = "select game_player.* from game_player where game_player.player1=?1 or game_player.player2=?2", nativeQuery = true)
    List<GamePlayer> findGamePlayerByPlayername (@Param("player1") String player1,
                                          @Param("player2") String player2);

    @Modifying
    @Transactional
    @Query(value = "update game_player set player=? where player1=? and player2=?", nativeQuery = true)
    int updatePlayerByGamePlayer(@Param("player") Integer player,
                        @Param("player1") String player1,
                        @Param("player2") String player2);
}
