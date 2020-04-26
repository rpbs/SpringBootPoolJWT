package murraco.dto;

import io.swagger.annotations.ApiModelProperty;

public class PoolDTO {

    @ApiModelProperty(position = 0)
    private String title;
    @ApiModelProperty(position = 1)
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
