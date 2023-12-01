package pageView.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class loginUserVo {
    private String token;

    private UserInfoVo userInfo;
}
