package murraco.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import murraco.dto.PoolDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pools")
@Api(tags = "pools")
public class PoolController {


    @PostMapping
    @ApiOperation(value = "${PoolController.create}")
    public void Create(@RequestBody PoolDTO dto){

    }

    @GetMapping
    @ApiOperation(value = "${PoolController.Get}")
    public void Get(Integer PoolId){

    }

}