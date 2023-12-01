package pageView.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pageView.domain.entity.Role;
import pageView.domain.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {

    private List<String> roleIds;

    private List<Role> roles;

    private User user;
}
