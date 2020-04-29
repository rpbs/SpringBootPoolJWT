package murraco.service;

import murraco.component.IAuthenticationFacade;
import murraco.model.Awnsers;
import murraco.repository.AwnserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwnserService {

    @Autowired
    IAuthenticationFacade authenticationFacade;
    @Autowired
    AwnserRepositoty awnserRepositoty;

    public Awnsers GetAwnser(Integer awnserId){
        return this.awnserRepositoty.findByAwnserIdEquals(awnserId);
    }

    public void UpdateAwnser(Awnsers p) {
        this.awnserRepositoty.saveAndFlush(p);
    }
}