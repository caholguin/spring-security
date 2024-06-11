package com.api.spring.security.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ADMINISTRATOR(Arrays.asList(
            RolePermisionEnum.READ_ALL_PRODUCTS,
            RolePermisionEnum.READ_ONE_PRODUCT,
            RolePermisionEnum.CREATE_ONE_PRODUCT,
            RolePermisionEnum.UPDATE_ONE_PRODUCT,
            RolePermisionEnum.DISABLED_ONE_PRODUCT,

            RolePermisionEnum.READ_ALL_CATEGORIES,
            RolePermisionEnum.READ_ONE_CATEGORY,
            RolePermisionEnum.CREATE_ONE_CATEGORY,
            RolePermisionEnum.UPDATE_ONE_CATEGORY,
            RolePermisionEnum.DISABLED_ONE_CATEGORY,

            RolePermisionEnum.READ_MY_PROFILE
    )),
    ASSISTANT_ADMINISTRATOR(Arrays.asList(
             RolePermisionEnum.READ_ALL_PRODUCTS,
             RolePermisionEnum.READ_ONE_PRODUCT,
             RolePermisionEnum.UPDATE_ONE_PRODUCT,

             RolePermisionEnum.READ_ALL_CATEGORIES,
             RolePermisionEnum.READ_ONE_CATEGORY,
             RolePermisionEnum.UPDATE_ONE_CATEGORY,

             RolePermisionEnum.READ_MY_PROFILE
    )),
    CUSTOMER(Arrays.asList(
            RolePermisionEnum.READ_MY_PROFILE
    ));

    private List<RolePermisionEnum> permisions;

    RoleEnum(List<RolePermisionEnum> permisions){
        this.permisions = permisions;
    }

    public List<RolePermisionEnum> getPermisions(){
        return permisions;
    }

    public void setPermisions(List<RolePermisionEnum> permisions){
        this.permisions = permisions;
    }
}
