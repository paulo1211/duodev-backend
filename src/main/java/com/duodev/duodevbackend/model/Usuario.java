package com.duodev.duodevbackend.model;

import com.duodev.duodevbackend.enums.Sexo;
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
    private Enum<Sexo> sexo;


    public String getNome() {
        return nome;
    }
    public int getID(){
        return id;
    }
    public String getCpf(){
        return cpf;
    }
    public String getEmail(){
        return email;
    }
    public String getSenha(){
        return senha;
    }
    public Enum getSexo(){
        return sexo;
    }



    public Usuario(int id, @NotNull String nome, @NotNull @CPF String cpf, @NotNull @Email String email,
            @NotNull String senha, @NotNull Enum<Sexo> sexo) {
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
    public int getId() {
        return id;
    }
    
}
