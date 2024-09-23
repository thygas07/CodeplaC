package codeplac.codeplac.Model;

import java.util.List;

import codeplac.codeplac.Enum.userTipo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class usersModel {

    @Id
    private int matricula;

    private String nome;
    private String sobrenome;

    @Enumerated(EnumType.STRING)
    private userTipo tipoUser;

    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private String refreshToken;

    @OneToMany(mappedBy = "usuario")

    private List<groupModel> grupos;

    @OneToMany(mappedBy = "usuario")
    private List<ticketModel> ingressos;

    @OneToMany(mappedBy = "usuario")
    private List<registrationModel> inscricoes;

}
