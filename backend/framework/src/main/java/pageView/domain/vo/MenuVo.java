package pageView.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {

    private String component;

    private String icon;

    private Long id;

    private String menuName;

    private String menuType;
    //显示顺序
    private Integer orderNum;

    private Long parentId;

    private String path;

    private String perms;

    private String remark;

    private String status;

    private String visible;
}
