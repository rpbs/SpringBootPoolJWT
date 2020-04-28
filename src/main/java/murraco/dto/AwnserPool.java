package murraco.dto;

public class AwnserPool {

    Integer PoolId;
    Integer OptionId;

    public AwnserPool() {

    }

    public AwnserPool(Integer poolId, Integer optionId) {
        PoolId = poolId;
        OptionId = optionId;
    }

    public Integer getPoolId() {
        return PoolId;
    }

    public void setPoolId(Integer poolId) {
        PoolId = poolId;
    }

    public Integer getOptionId() {
        return OptionId;
    }

    public void setOptionId(Integer optionId) {
        OptionId = optionId;
    }
}
