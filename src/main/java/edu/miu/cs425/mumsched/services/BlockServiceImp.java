package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.dao.BlockDao;
import edu.miu.cs425.mumsched.domain.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Service
public class BlockServiceImp implements BlockService{
    @Autowired
    BlockDao blockDao;
    @Override
    public void save(Block block) {
        blockDao.save(block);
        return;
    }
    @Override
    public void delete(Block block) {
        blockDao.delete(block);
        return;
    }
    @Override
    public Block getBlockByBlockID(long blockId) {
        return blockDao.findBlockByBlockID(blockId);
    }

    @Override
    public Block getBlockByBlockName(String blockName) {
        return blockDao.findBlockByBlockName(blockName);
    }

    @Override
    public List<Block> getAllBlock() {
        return blockDao.getAllBlocks();
    }
}
