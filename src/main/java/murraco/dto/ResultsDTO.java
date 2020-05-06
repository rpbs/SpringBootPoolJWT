package murraco.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ResultsDTO {
    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String title;
    @ApiModelProperty(position = 2)
    private String description;
    @ApiModelProperty(position = 3)
    private List<AwnsersDataDTO> options;
    @ApiModelProperty(position = 3)
    private String creator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AwnsersDataDTO> getOptions() {
        return options;
    }

    public void setOptions(List<AwnsersDataDTO> options) {
        this.options = options;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}