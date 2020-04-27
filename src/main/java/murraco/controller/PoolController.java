package murraco.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import murraco.dto.PoolDTO;
import murraco.model.Pool;
import murraco.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pools")
@Api(tags = "pools")
public class PoolController {

    @Autowired
    PoolService poolService;

    @PostMapping
    @ApiOperation(value = "${PoolController.create}")
    public void Create(@RequestBody PoolDTO dto){
        poolService.CreatePool(dto);
    }

    @GetMapping
    @ApiOperation(value = "${PoolController.Get}")
    public PoolDTO Get(Integer poolId){
        PoolDTO novo = new PoolDTO();
        Pool p = poolService.GetPool(poolId);
        novo.setId(poolId);
        novo.setDescription(p.getDescription());
        novo.setTitle(p.getDescription());
        return novo;
    }

}