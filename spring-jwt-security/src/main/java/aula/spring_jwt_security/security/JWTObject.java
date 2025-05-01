package aula.spring_jwt_security.security;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JWTObject {
    private String subject; // Nome do usuário
    private Date issueAt; // Data de emissão
    private Date expiration; // Data de expiração
    private List<String> roles; // Roles do usuário


    public void setRoles(String... roles) { // Múltiplos argumentos
        this.roles = Arrays.asList(roles); // Converte para uma lista
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(Date issueAt) {
        this.issueAt = issueAt;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
