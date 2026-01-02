package top.potat.spring.common.domain.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageBase {
    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;
    public PageBase() {
        this.pageNum = 1;
        this.pageSize = 10;
    }
}
