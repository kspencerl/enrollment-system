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
                         tipoUsuario tipo_usuario NOT NULL
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
                            quantidadeAlunos INT DEFAULT 0,
                            FOREIGN KEY (id_professor) REFERENCES Professor(id_usuario) ON DELETE CASCADE,
                            FOREIGN KEY (id_curso) REFERENCES Curso(id) ON DELETE CASCADE
);

CREATE TABLE Periodo_Matricula (
                                   id SERIAL PRIMARY KEY,
                                   dataInicio DATE NOT NULL,
                                   dataFim DATE NOT NULL,
                                   status status_periodo DEFAULT 'ABERTO'
);

CREATE TABLE Matricula (
                           id SERIAL PRIMARY KEY,
                           id_aluno INT NOT NULL,
                           id_disciplina INT NOT NULL,
                           id_periodo INT NOT NULL,
                           status status_matricula NOT NULL DEFAULT 'INICIADA',
                           dataMatricula TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (id_aluno) REFERENCES Aluno(id_usuario) ON DELETE CASCADE,
                           FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id) ON DELETE CASCADE,
                           FOREIGN KEY (id_periodo) REFERENCES Periodo_Matricula(id) ON DELETE CASCADE,
                           UNIQUE (id_aluno, id_disciplina, id_periodo)
);

CREATE TABLE Cobranca (
                          id SERIAL PRIMARY KEY,
                          id_matricula INT NOT NULL,
                          valor DECIMAL(10,2) NOT NULL,
                          status status_cobranca DEFAULT 'PENDENTE',
                          data_geracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (id_matricula) REFERENCES Matricula(id) ON DELETE CASCADE
);

-- Inserção de dados iniciais

-- Inserir usuários (10 usuários)
INSERT INTO Usuario (nome, email, senha, tipoUsuario) VALUES
                                                   ('João Silva', 'joao@email.com', 'senha123', 'ALUNO'),
                                                   ('Maria Oliveira', 'maria@email.com', 'senha456', 'PROFESSOR'),
                                                   ('Carlos Santos', 'carlos@email.com', 'senha789', 'SECRETARIA'),
                                                   ('Ana Souza', 'ana@email.com', 'senha111', 'ALUNO'),
                                                   ('Pedro Lima', 'pedro@email.com', 'senha222', 'ALUNO'),
                                                   ('Fernanda Alves', 'fernanda@email.com', 'senha333', 'ALUNO'),
                                                   ('Ricardo Mendes', 'ricardo@email.com', 'senha444', 'PROFESSOR'),
                                                   ('Juliana Costa', 'juliana@email.com', 'senha555', 'PROFESSOR'),
                                                   ('Gabriel Rocha', 'gabriel@email.com', 'senha666', 'SECRETARIA'),
                                                   ('Beatriz Lima', 'beatriz@email.com', 'senha777', 'SECRETARIA');

-- Inserir cursos (4 cursos)
INSERT INTO Curso (nome, creditos) VALUES
                                       ('Engenharia de Software', 200),
                                       ('Ciência da Computação', 180),
                                       ('Sistemas de Informação', 160),
                                       ('Análise e Desenvolvimento de Sistemas', 140);

-- Inserir alunos (4 alunos)
INSERT INTO Aluno (id_usuario, id_curso) VALUES
                                             (1, 1), -- João Silva (Engenharia de Software)
                                             (4, 2), -- Ana Souza (Ciência da Computação)
                                             (5, 3), -- Pedro Lima (Sistemas de Informação)
                                             (6, 4); -- Fernanda Alves (Análise e Desenvolvimento de Sistemas)

-- Inserir professores (3 professores)
INSERT INTO Professor (id_usuario) VALUES
                                       (2), -- Maria Oliveira
                                       (7), -- Ricardo Mendes
                                       (8); -- Juliana Costa

-- Inserir disciplinas (4 disciplinas)
INSERT INTO Disciplina (nome, id_curso, id_professor, credito, valor, categoria) VALUES
                                                                                     ('Banco de Dados', 1, 2, 4, 500.00, 'OBRIGATORIA'),
                                                                                     ('Programação Web', 2, 2, 5, 600.00, 'OBRIGATORIA'),
                                                                                     ('Inteligência Artificial', 3, 2, 4, 700.00, 'OPTATIVA'),
                                                                                     ('Computação em Nuvem', 4, 7, 3, 800.00, 'OPTATIVA');



-- Inserir períodos de matrícula
INSERT INTO Periodo_Matricula (dataInicio, dataFim, status) VALUES
                                                          ('2024-01-01', '2024-02-01', 'FECHADO'),
                                                          ('2024-07-01', '2024-08-01', 'FECHADO');

INSERT INTO Matricula (id_aluno, id_disciplina, id_periodo, status, dataMatricula) VALUES
                                                                                       (1, 1, 1, 'INICIADA', '2025-02-27 10:00:00'), -- João em Banco de Dados
                                                                                       (4, 2, 1, 'INICIADA', '2025-02-27 10:05:00'), -- Ana em Programação Web
                                                                                       (5, 3, 2, 'INICIADA', '2025-02-27 10:10:00'), -- Pedro em IA
                                                                                       (6, 4, 2, 'INICIADA', '2025-02-27 10:15:00'); -- Fernanda em Computação em Nuvem


INSERT INTO Cobranca (id_matricula, valor, status, data_geracao) VALUES
                                                                     (1, 1500.00, 'PENDENTE', '2025-02-27 10:00:00'),
                                                                     (2, 1200.00, 'PENDENTE', '2025-02-27 10:05:00'),
                                                                     (3, 1800.00, 'PENDENTE', '2025-02-27 10:10:00'),
                                                                     (4, 1600.00, 'PENDENTE', '2025-02-27 10:15:00');


