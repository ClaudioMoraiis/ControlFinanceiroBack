package com.controleFinanceiro.ControleFinanceiro.user;

public enum UserROle {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserROle(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
