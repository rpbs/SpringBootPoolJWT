package murraco.service;

import murraco.dto.PoolDTO;
import murraco.exception.CustomException;
import murraco.model.Pool;
import murraco.model.User;
import murraco.repository.PoolRepository;
import murraco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PoolService {

    @Autowired
    private PoolRepository poolRepository;
    @Autowired
    private UserRepository userRepository;

    public void CreatePool(PoolDTO dto){
        try {
            //final Pool p = this.poolRepository.findByIdEquals(dto.getId());
            final User u = this.userRepository.findByIdEquals(dto.getId());

            Pool novo = new Pool();

            novo.setCreatedDate(new Date());
            novo.setTitle(dto.getTitle());
            novo.setDescription(dto.getDescription());
            novo.setOptions(dto.);

            final Pool p = this.poolRepository.save(new Pool(){

            });

        }
        catch (Exception e){
            throw new CustomException("erro ao cadastrar", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }
}
