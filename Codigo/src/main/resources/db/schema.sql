-- Criar tipos ENUM
DROP TYPE IF EXISTS tipo_usuario CASCADE;
CREATE TYPE tipo_usuario AS ENUM ('ALUNO', 'PROFESSOR', 'SECRETARIA');

DROP TYPE IF EXISTS status_disciplina CASCADE;
CREATE TYPE status_disciplina AS ENUM ('ATIVA', 'CANCELADA');

DROP TYPE IF EXISTS categoria_disciplina CASCADE;
CREATE TYPE categoria_disciplina AS ENUM ('OBRIGATORIA', 'OPTATIVA');

DROP TYPE IF EXISTS status_periodo CASCADE;
CREATE TYPE status_periodo AS ENUM ('ABERTO', 'FECHADO');

DROP TYPE IF EXISTS status_matricula CASCADE;
CREATE TYPE status_matricula AS ENUM ('INICIADA', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA');

DROP TYPE IF EXISTS status_cobranca CASCADE;
CREATE TYPE status_cobranca AS ENUM ('PENDENTE', 'PAGA');

-- Criar tabelas
DROP TABLE IF EXISTS Cobranca CASCADE;
DROP TABLE IF EXISTS Matricula CASCADE;
DROP TABLE IF EXISTS Periodo_Matricula CASCADE;
DROP TABLE IF EXISTS Disciplina CASCADE;
DROP TABLE IF EXISTS Professor CASCADE;
DROP TABLE IF EXISTS Aluno CASCADE;
DROP TABLE IF EXISTS Curso CASCADE;
DROP TABLE IF EXISTS Usuario CASCADE;

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
                       id_usuario INT PRIMARY KEY,
                       id_curso INT NOT NULL,
                       FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
                       FOREIGN KEY (id_curso) REFERENCES Curso(id) ON DELETE CASCADE
);

CREATE TABLE Professor (
                           id_usuario INT PRIMARY KEY,
                           FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE
);


CREATE TABLE Secretaria (
                            id_usuario INT PRIMARY KEY,
                            FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE
);

CREATE TABLE Disciplina (
                            id SERIAL PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            id_curso INT NOT NULL,
                            id_professor INT NOT NULL,
                            credito INT NOT NULL,
                            valor DECIMAL(10,2) NOT NULL,
                            status status_disciplina DEFAULT 'ATIVA',
                            categoria categoria_disciplina NOT NULL,
                            quantidade_alunos INT DEFAULT 0,
                            FOREIGN KEY (id_professor) REFERENCES Professor(id_usuario) ON DELETE CASCADE,
                            FOREIGN KEY (id_curso) REFERENCES Curso(id) ON DELETE CASCADE
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
                           id_periodo INT NOT NULL,
                           status status_matricula NOT NULL DEFAULT 'INICIADA',
                           data_matricula TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (id_aluno) REFERENCES Aluno(id_usuario) ON DELETE CASCADE,
                           FOREIGN KEY (id_periodo) REFERENCES Periodo_Matricula(id) ON DELETE CASCADE,
                           UNIQUE (id_aluno, id_periodo)
);


CREATE TABLE Matricula_Disciplina (
                                      id SERIAL PRIMARY KEY,
                                      id_matricula INT NOT NULL,
                                      id_disciplina INT NOT NULL,
                                      FOREIGN KEY (id_matricula) REFERENCES Matricula(id) ON DELETE CASCADE,
                                      FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id) ON DELETE CASCADE,
                                      UNIQUE (id_matricula, id_disciplina)
);


CREATE TABLE Cobranca (
                          id SERIAL PRIMARY KEY,
                          id_matricula INT NOT NULL,
                          valor DECIMAL(10,2) NOT NULL,
                          status status_cobranca DEFAULT 'PENDENTE',
                          data_geracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (id_matricula) REFERENCES Matricula(id) ON DELETE CASCADE
);
