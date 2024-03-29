package com.michael.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * desc
 *
 * @author wangce 2021-05-23
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void test(){
        //对原始密码加密
        String hashpw = BCrypt.hashpw("secret",BCrypt.gensalt());
        System.out.println(hashpw);
        //校验原始密码和BCrypt密码是否一致
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm");
        System.out.println(checkpw);
    }
}
