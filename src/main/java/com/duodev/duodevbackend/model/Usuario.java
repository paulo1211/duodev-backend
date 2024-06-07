package com.duodev.duodevbackend.model;
import com.duodev.duodevbackend.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @NotNull
    @CPF (message = "CPF inválido")

    @Column(unique = true)
    private String cpf;

    @NotNull
    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "A senha deve ser informada")
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres")
    private String senha;

    @NotNull
    private Sexo sexo;

    public Usuario(int id, @NotNull String nome, @NotNull @CPF String cpf, @NotNull @Email String email,
            @NotNull String senha, @NotNull Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
    }

    public Usuario() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
