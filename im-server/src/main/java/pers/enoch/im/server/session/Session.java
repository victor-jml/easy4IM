package pers.enoch.im.server.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zy
 * @Date: 2020/11/30 23:36
 * @Description:
 */
@Data
@NoArgsConstructor
public class Session {

    private String userId;

    private String userName;

    public Session(String userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + " : " + userName;
    }
}
