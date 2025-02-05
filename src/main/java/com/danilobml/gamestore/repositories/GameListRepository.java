package com.danilobml.gamestore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danilobml.gamestore.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
    void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);

    @Modifying
    @Query(nativeQuery = true, value = """
                INSERT INTO tb_belonging (list_id, game_id, position)
                VALUES (
                    :listId,
                    :gameId,
                    (SELECT COALESCE(MAX(position), 0) + 1 FROM tb_belonging WHERE list_id = :listId)
                )
            """)
    void addGameToList(@Param("listId") Long listId, @Param("gameId") Long gameId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM tb_belonging WHERE list_id = :listId AND game_id = :gameId")
    void removeGameFromList(@Param("listId") Long listId, @Param("gameId") Long gameId);

    @Query(nativeQuery = true, value = "SELECT position FROM tb_belonging WHERE list_id = :listId AND game_id = :gameId")
    Optional<Integer> findGamePosition(@Param("listId") Long listId, @Param("gameId") Long gameId);

    @Query(nativeQuery = true, value = "SELECT COALESCE(MAX(position), 0) FROM tb_belonging WHERE list_id = :listId")
    Optional<Integer> findMaxPositionInList(@Param("listId") Long listId);

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE tb_belonging
                SET position = position - 1
                WHERE list_id = :listId AND position > (
                    SELECT position FROM tb_belonging WHERE list_id = :listId AND game_id = :gameId
                )
            """)
    void shiftPositionsAfterRemoval(@Param("listId") Long listId, @Param("gameId") Long gameId);

}