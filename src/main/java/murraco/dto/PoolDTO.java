package murraco.dto;

import io.swagger.annotations.ApiModelProperty;
import murraco.model.Options;

import java.util.List;

public class PoolDTO {

    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String title;
    @ApiModelProperty(position = 2)
    private String description;
    @ApiModelProperty(position = 3)
    private List<OptionsDTO> options;
    @ApiModelProperty(position = 3)
    private String creator;

    public Integer getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OptionsDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsDTO> options) {
        this.options = options;
    }
}
