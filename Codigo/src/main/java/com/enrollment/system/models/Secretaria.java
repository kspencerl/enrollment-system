package com.enrollment.system.models;

import com.enrollment.system.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="secretaria")
@PrimaryKeyJoinColumn(name = "id_usuario")
@DiscriminatorValue("secretaria")
public class Secretaria extends Usuario {}

