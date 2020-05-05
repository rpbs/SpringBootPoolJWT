package murraco.service;

import murraco.component.IAuthenticationFacade;
import murraco.model.Awnsers;
import murraco.model.Options;
import murraco.model.Pool;
import murraco.model.User;
import murraco.repository.AwnserRepositoty;
import murraco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwnserService {

    @Autowired
    IAuthenticationFacade authenticationFacade;
    @Autowired
    AwnserRepositoty awnserRepositoty;
    @Autowired
    UserRepository userRepository;

    public Awnsers GetAwnser(Integer awnserId){
        return this.awnserRepositoty.findByAwnserIdEquals(awnserId);
    }

    public void UpdateAwnser(Awnsers p) {
        this.awnserRepositoty.saveAndFlush(p);
    }

    public Boolean isAnsered(Pool p, Options o){
        String nome = authenticationFacade.getAuthentication().getName();
        final User u = this.userRepository.findByUsername(nome);
        return this.awnserRepositoty.existsByPoolAndOptionAndUser(p, o, u);
    }
}
