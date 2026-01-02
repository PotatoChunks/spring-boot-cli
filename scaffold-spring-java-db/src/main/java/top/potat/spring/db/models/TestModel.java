package top.potat.spring.db.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class TestModel {


    @Data
    public static class PostTest{
        private String name;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class GetTest extends PostTest{
        private Integer num;
    }
}
