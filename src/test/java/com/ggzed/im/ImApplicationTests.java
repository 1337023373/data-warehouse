package com.ggzed.im;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.StopWatch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImApplicationTests {

    @Test
    public void contextLoads() {


        Integer i = 5;
        String s = String.valueOf(i);
        String string = i.toString();
        Double aDouble = Double.valueOf(i);
        Long aLong = Long.valueOf(i);
        System.out.println(aLong);

        //把错误版本改成正确版本，再次提交


    }

}
