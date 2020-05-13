package edu.miu.cs425.mumsched.services;


import edu.miu.cs425.mumsched.domain.Block;

import java.util.List;
import java.util.Optional;

public interface BlockService {
    void save(Block block);
    Block getBlockByBlockID(long blockId);
    Block getBlockByBlockName(String blockName);
    List<Block> getAllBlock();
    void delete(Block block);
}
