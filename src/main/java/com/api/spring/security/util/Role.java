package com.api.spring.security.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermision.READ_ALL_PRODUCTS,
            RolePermision.READ_ONE_PRODUCT,
            RolePermision.CREATE_ONE_PRODUCT,
            RolePermision.UPDATE_ONE_PRODUCT,
            RolePermision.DISABLED_ONE_PRODUCT,

            RolePermision.READ_ALL_CATEGORIES,
            RolePermision.READ_ONE_CATEGORY,
            RolePermision.CREATE_ONE_CATEGORY,
            RolePermision.UPDATE_ONE_CATEGORY,
            RolePermision.DISABLED_ONE_CATEGORY,

            RolePermision.READ_MY_PROFILE
    )),
    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
             RolePermision.READ_ALL_PRODUCTS,
             RolePermision.READ_ONE_PRODUCT,
             RolePermision.UPDATE_ONE_PRODUCT,

             RolePermision.READ_ALL_CATEGORIES,
             RolePermision.READ_ONE_CATEGORY,
             RolePermision.UPDATE_ONE_CATEGORY,

             RolePermision.READ_MY_PROFILE
    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermision.READ_MY_PROFILE
    ));

    private List<RolePermision> permisions;

    Role(List<RolePermision> permisions){
        this.permisions = permisions;
    }

    public List<RolePermision> getPermisions(){
        return permisions;
    }

    public void setPermisions(List<RolePermision> permisions){
        this.permisions = permisions;
    }
}
