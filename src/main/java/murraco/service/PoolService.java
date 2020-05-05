package murraco.service;

import murraco.component.IAuthenticationFacade;
import murraco.dto.PoolDTO;
import murraco.exception.CustomException;
import murraco.model.Awnsers;
import murraco.model.Options;
import murraco.model.Pool;
import murraco.model.User;
import murraco.repository.AwnserRepositoty;
import murraco.repository.OptionRepository;
import murraco.repository.PoolRepository;
import murraco.repository.UserRepository;
import murraco.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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
    @Autowired
    AwnserRepositoty awnserRepositoty;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    IAuthenticationFacade authenticationFacade;


    @Transactional
    public Pool CreatePool(PoolDTO dto){
        try {

            String nome = authenticationFacade.getAuthentication().getName();
            final User u = this.userRepository.findByUsername(nome);

            final Pool novo = new Pool();

            novo.setCreator(u);
            novo.setCreatedDate(new Date());
            novo.setTitle(dto.getTitle());
            novo.setDescription(dto.getDescription());

            Pool p = this.poolRepository.save(novo);

            List<Options> opcoes = new LinkedList<Options>();

            dto.getOptions().forEach(opcao -> {
                if (!opcao.getDescription().isEmpty())
                    opcoes.add(new Options(novo, opcao.getDescription()));
            });

            if (opcoes.size() <= 1){
                throw new Exception("Can't create poll with one option only");
            }

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

    public void AwnserPool(Integer poolId, Integer optionId){
        Awnsers novo = new Awnsers();

        final Pool p = this.poolRepository.findByIdEquals(poolId);
        final Options o = this.optionRepository.findById(optionId).get();
        String nome = authenticationFacade.getAuthentication().getName();
        final User u = this.userRepository.findByUsername(nome);

        novo.setOption(o);
        novo.setPool(p);
        novo.setUser(u);

        awnserRepositoty.save(novo);
    }

    public Awnsers GetPoolUserAwnser(Integer poolId) {
        String nome = authenticationFacade.getAuthentication().getName();
        final User u = this.userRepository.findByUsername(nome);
        final Pool p = this.poolRepository.findByIdEquals(poolId);
        Awnsers a = this.awnserRepositoty.findByPoolAndUser(p, u);
        return a;
    }
}
