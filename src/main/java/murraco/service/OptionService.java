package murraco.service;

import murraco.model.Options;
import murraco.model.Pool;
import murraco.repository.OptionRepository;
import murraco.repository.PoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

public class OptionService {

    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private PoolRepository poolRepository;


    public void CreateOption(Integer poolId, String option){
        Options op = new Options();
        op.setDescription(option);
        final Pool p = this.poolRepository.findByIdEquals(poolId);
        op.setPool(p);
        this.optionRepository.save(op);
    }
}
