package edu.miu.cs425.mumsched.dao;

import edu.miu.cs425.mumsched.domain.Block;
import edu.miu.cs425.mumsched.domain.Entry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Repository
@Transactional
public interface BlockDao extends CrudRepository<Block,Long> {
    Block findBlockByBlockID(long blockId);
    Block findBlockByBlockName(String blockName);
    @Query("select b from Block b")
    List<Block> getAllBlocks();
}
