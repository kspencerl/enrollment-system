CREATE DATABASE Universidade;
\c Universidade;

CREATE TYPE tipo_usuario AS ENUM ('ALUNO', 'PROFESSOR', 'SECRETARIA');
CREATE TYPE status_disciplina AS ENUM ('ATIVA', 'CANCELADA');
CREATE TYPE categoria_disciplina AS ENUM ('OBRIGATORIA', 'OPTATIVA');
CREATE TYPE status_periodo AS ENUM ('ABERTO', 'FECHADO');
CREATE TYPE status_matricula AS ENUM ('INICIADA', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA');
CREATE TYPE status_cobranca AS ENUM ('PENDENTE', 'PAGA');

CREATE TABLE Usuario (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         tipo tipo_usuario NOT NULL
);

CREATE TABLE Curso (
                       id SERIAL PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       creditos INT NOT NULL
);

CREATE TABLE Aluno (
                       id SERIAL PRIMARY KEY,
                       id_usuario INT NOT NULL,
                       id_curso INT NOT NULL,
                       FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
                       FOREIGN KEY (id_curso) REFERENCES Curso(id) ON DELETE CASCADE
);

CREATE TABLE Professor (
                           id SERIAL PRIMARY KEY,
                           id_usuario INT NOT NULL,
                           FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE
);

CREATE TABLE Disciplina (
                            id SERIAL PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            id_curso INT NOT NULL,
                            creditos INT NOT NULL,
                            capacidade_maxima INT DEFAULT 60,
                            capacidade_minima INT DEFAULT 3,
                            status status_disciplina DEFAULT 'ATIVA',
                            categoria categoria_disciplina NOT NULL,
                            FOREIGN KEY (id_curso) REFERENCES Curso(id) ON DELETE CASCADE
);

CREATE TABLE Professor_Disciplina (
                                      id_professor INT NOT NULL,
                                      id_disciplina INT NOT NULL,
                                      PRIMARY KEY (id_professor, id_disciplina),
                                      FOREIGN KEY (id_professor) REFERENCES Professor(id) ON DELETE CASCADE,
                                      FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id) ON DELETE CASCADE
);

CREATE TABLE Periodo_Matricula (
                                   id SERIAL PRIMARY KEY,
                                   data_inicio DATE NOT NULL,
                                   data_fim DATE NOT NULL,
                                   status status_periodo DEFAULT 'ABERTO'
);

CREATE TABLE Matricula (
                           id SERIAL PRIMARY KEY,
                           id_aluno INT NOT NULL,
                           id_disciplina INT NOT NULL,
                           id_periodo INT NOT NULL,
                           status status_matricula NOT NULL DEFAULT 'INICIADA',
                           data_matricula TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (id_aluno) REFERENCES Aluno(id) ON DELETE CASCADE,
                           FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id) ON DELETE CASCADE,
                           FOREIGN KEY (id_periodo) REFERENCES Periodo_Matricula(id) ON DELETE CASCADE,
                           UNIQUE (id_aluno, id_disciplina)
);

CREATE TABLE Cobranca (
                          id SERIAL PRIMARY KEY,
                          id_matricula INT NOT NULL,
                          valor DECIMAL(10,2) NOT NULL,
                          status status_cobranca DEFAULT 'PENDENTE',
                          data_geracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (id_matricula) REFERENCES Matricula(id) ON DELETE CASCADE
);
