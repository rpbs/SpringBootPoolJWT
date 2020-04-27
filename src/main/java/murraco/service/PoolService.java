package murraco.service;

import murraco.dto.PoolDTO;
import murraco.exception.CustomException;
import murraco.model.Options;
import murraco.model.Pool;
import murraco.model.User;
import murraco.repository.OptionRepository;
import murraco.repository.PoolRepository;
import murraco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PoolService {

    @Autowired
    private PoolRepository poolRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    OptionRepository optionRepository;


    @Transactional
    public Pool CreatePool(PoolDTO dto){
        try {
            //final Pool p = this.poolRepository.findByIdEquals(dto.getId());
            final User u = this.userRepository.findByIdEquals(dto.getId());

            Pool novo = new Pool();

            novo.setCreator(u);
            novo.setCreatedDate(new Date());
            novo.setTitle(dto.getTitle());
            novo.setDescription(dto.getDescription());

            Pool p = this.poolRepository.save(novo);

            List<Options> opcoes = new LinkedList<Options>();

            dto.getOptions().forEach(opcao -> {
                opcoes.add(new Options(novo, opcao.getDescription()));
            });

            p.setOptions(opcoes);

            p.setOptions(this.optionRepository.saveAll(opcoes));

            p = this.poolRepository.save(p);

            return p;
        }
        catch (Exception e){
            throw new CustomException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Pool GetPool(Integer poolId){
        Pool p = poolRepository.findByIdEquals(poolId);
        return p;
    }

    public List<Pool> GetPool(){
        return this.poolRepository.findAll();
    }
}
