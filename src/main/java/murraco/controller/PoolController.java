package murraco.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import murraco.dto.*;
import murraco.model.Awnsers;
import murraco.model.Options;
import murraco.model.Pool;
import murraco.service.AwnserService;
import murraco.service.OptionService;
import murraco.service.PoolService;
import murraco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pools")
@Api(tags = "pools")
public class PoolController {

    @Autowired
    PoolService poolService;
    @Autowired
    AwnserService awnserService;
    @Autowired
    OptionService optionService;
    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation(value = "${PoolController.create}")
    public void Create(@RequestBody PoolDTO dto){
        poolService.CreatePool(dto);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Return all pools")
    public List<PoolDTO> Get(){

        List<PoolDTO> data = new ArrayList();
        List<Pool> pools = poolService.GetPool();

        for (Pool p : pools){
            data.add(this.Mapper(p));
        }

        return data;
    }

    @GetMapping
    @ApiOperation(value = "${PoolController.Get}")
    public PoolDTO Get(Integer poolId){
        PoolDTO novo = new PoolDTO();
        Pool p = poolService.GetPool(poolId);
        novo.setDescription(p.getDescription());
        novo.setTitle(p.getDescription());
        List<Options> x = p.getOptions();

        List<OptionsDTO> optionsDTOS = new ArrayList<>();

        for (Options o : x){
            final Boolean respondido = this.awnserService.isAnsered(p, o);
            optionsDTOS.add(new OptionsDTO(o.getOptionId(), o.getDescription(), respondido));
        }

        novo.setOptions(optionsDTOS);

        return novo;
    }

    @PostMapping("/anwser")
    @ApiOperation(value = "Anwser a pool based on its choice")
    public void AwnserPool(@RequestBody AwnserPool awnserPool){
        this.poolService.AwnserPool(awnserPool.getPoolId(), awnserPool.getOptionId());
    }

    @PutMapping("/edit/{poolId}/awnser/{optionId}")
    @ApiOperation(value = "Awnser a pool based on its choice")
    public void EditAnserPool(@PathVariable Integer poolId, @PathVariable Integer optionId) throws Exception {
        Awnsers p = this.poolService.GetPoolUserAwnser(poolId);
        Options o = this.optionService.GetOption(optionId);
        if (p.getPool().getId() == o.getPool().getId()) {
            p.setOption(o);
            this.awnserService.UpdateAwnser(p);
        }
        else throw new Exception("Option not belong to the pool");
    }

    @GetMapping("/results")
    @ApiOperation(value = "Shows data for a chart")
    public List<ResultsDTO> Results() throws Exception {
        List<ResultsDTO> results = new ArrayList<>();
        List<Pool> pools = poolService.GetPool();

        for (Pool p : pools){
            List<Awnsers> awnsers = awnserService.GetAllAwnserByPool(p);
            ResultsDTO r = new ResultsDTO();

            r.setCreator(p.getCreator().getUsername());
            r.setDescription(p.getDescription());
            r.setId(p.getId());
            r.setTitle(p.getTitle());

            List<AwnsersDataDTO> list = new ArrayList<>();

            for (Options o : p.getOptions()){
                AwnsersDataDTO dto = new AwnsersDataDTO();
                Integer qnt = this.awnserService.countVotesByOption(o);
                dto.setOption(o.getDescription());
                dto.setQntVotes(qnt);
                list.add(dto);
            }

            r.setOptions(list);

            results.add(r);
        }

        return results;
    }

    private PoolDTO Mapper(Pool p){
        PoolDTO dto = new PoolDTO();
        dto.setId(p.getId());
        dto.setDescription(p.getDescription());
        dto.setTitle(p.getTitle());
        dto.setCreator(p.getCreator().getUsername());
        List<OptionsDTO> optionsDTOS = new ArrayList<>();

        for (Options o : p.getOptions()){
            final Boolean respondido = this.awnserService.isAnsered(p, o);
            optionsDTOS.add(new OptionsDTO(o.getOptionId(), o.getDescription(), respondido));
        }

        dto.setOptions(optionsDTOS);

        return dto;
    }

}