package murraco.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import murraco.dto.AwnserPool;
import murraco.dto.OptionsDTO;
import murraco.dto.PoolDTO;
import murraco.model.Awnsers;
import murraco.model.Options;
import murraco.model.Pool;
import murraco.service.AwnserService;
import murraco.service.OptionService;
import murraco.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping
    @ApiOperation(value = "${PoolController.create}")
    public void Create(@RequestBody PoolDTO dto){
        poolService.CreatePool(dto);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Return all pools")
    public List<PoolDTO> Get(){

        List<PoolDTO> data = new ArrayList<PoolDTO>();

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
        novo.setId(poolId);
        novo.setDescription(p.getDescription());
        novo.setTitle(p.getDescription());
        List<Options> x = p.getOptions();

        List<OptionsDTO> optionsDTOS = new ArrayList<>();

        for (Options o : x){
            optionsDTOS.add(new OptionsDTO(o.getOptionId(), o.getDescription()));
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
    @ApiOperation(value = "Anwser a pool based on its choice")
    public void EditAnserPool(@PathVariable Integer poolId, @PathVariable Integer optionId) throws Exception {
        Awnsers p = this.poolService.GetPoolUserAwnser(poolId);
        Options o = this.optionService.GetOption(optionId);
        if (p.getPool().getId() == o.getPool().getId()) {
            p.setOption(o);
            this.awnserService.UpdateAwnser(p);
        }
        else throw new Exception("Option not belong to the pool");
    }


    private PoolDTO Mapper(Pool p){
        PoolDTO dto = new PoolDTO();
        dto.setId(p.getId());
        dto.setDescription(p.getDescription());
        dto.setTitle(p.getTitle());

        List<OptionsDTO> optionsDTOS = new ArrayList<>();

        for (Options o : p.getOptions()){
            optionsDTOS.add(new OptionsDTO(o.getOptionId(), o.getDescription()));
        }

        dto.setOptions(optionsDTOS);

        return dto;
    }

}