package com.playground.spring.vo;

public class BuilderPatternVo {
    private String name;
    private int age;
    private String address;
    private String phone;

    private BuilderPatternVo() {
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {
        private final BuilderPatternVo builderPatternVo;

        public Builder() {
            builderPatternVo = new BuilderPatternVo();
        }

        public BuilderPatternVo build() {
            return builderPatternVo;
        }

        public Builder name(String name) {
            builderPatternVo.name = name;
            return this;
        }

        public Builder age(int age) {
            builderPatternVo.age = age;
            return this;
        }

        public Builder address(String address) {
            builderPatternVo.address = address;
            return this;
        }

        public Builder phone(String phone) {
            builderPatternVo.phone = phone;
            return this;
        }
    }

    @Override
    public String toString() {
        return "BuilderPatternVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static void main(String[] args) {
        BuilderPatternVo vo = BuilderPatternVo.builder()
                .name("John")
                .age(30)
                .address("Seoul")
                .phone("010-1234-5678")
                .build();
        System.out.println(vo);
    }
}
