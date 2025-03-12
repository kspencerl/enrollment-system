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
                          data_vencimento DATE NOT NULL, -- Corrigido, adicionando o tipo de dado DATE
                          FOREIGN KEY (id_matricula) REFERENCES Matricula(id) ON DELETE CASCADE
);

-- Inserir usuários e associá-los às tabelas correspondentes
INSERT INTO Usuario (nome, email, senha, tipo) VALUES
                                                   ('João Silva', 'joao@email.com', 'senha123', 'ALUNO'),
                                                   ('Maria Oliveira', 'maria@email.com', 'senha456', 'PROFESSOR'),
                                                   ('Carlos Santos', 'carlos@email.com', 'senha789', 'SECRETARIA'),
                                                   ('Ana Souza', 'ana@email.com', 'senha111', 'ALUNO'),
                                                   ('Pedro Lima', 'pedro@email.com', 'senha222', 'ALUNO'),
                                                   ('Fernanda Alves', 'fernanda@email.com', 'senha333', 'ALUNO'),
                                                   ('Ricardo Mendes', 'ricardo@email.com', 'senha444', 'PROFESSOR'),
                                                   ('Juliana Costa', 'juliana@email.com', 'senha555', 'PROFESSOR'),
                                                   ('Gabriel Rocha', 'gabriel@email.com', 'senha666', 'SECRETARIA'),
                                                   ('Beatriz Lima', 'beatriz@email.com', 'senha777', 'ALUNO');


-- Inserir cursos
INSERT INTO Curso (nome, creditos) VALUES
                                       ('Engenharia da Computação', 240),
                                       ('Administração', 200),
                                       ('Direito', 220);

-- Associar alunos a um curso (supondo que o ID dos cursos seja 1, 2 ou 3)
INSERT INTO Aluno (id_usuario, id_curso) VALUES
                                             (1, 1), -- João Silva - Engenharia da Computação
                                             (4, 2), -- Ana Souza - Administração
                                             (5, 3), -- Pedro Lima - Direito
                                             (6, 1), -- Fernanda Alves - Engenharia da Computação
                                             (10, 2); -- Beatriz Lima - Administração

-- Associar professores
INSERT INTO Professor (id_usuario) VALUES
                                       (2), -- Maria Oliveira
                                       (7), -- Ricardo Mendes
                                       (8); -- Juliana Costa

-- Associar funcionários da secretaria
INSERT INTO Secretaria (id_usuario) VALUES
                                        (3), -- Carlos Santos
                                        (9); -- Gabriel Rocha

-- Inserir disciplinas
INSERT INTO Disciplina (nome, id_curso, id_professor, credito, valor, categoria) VALUES
                                                                                     ('Banco de Dados', 1, 2, 4, 500.00, 'OBRIGATORIA'),
                                                                                     ('Gestão Financeira', 2, 7, 3, 400.00, 'OPTATIVA'),
                                                                                     ('Direito Penal', 3, 8, 5, 600.00, 'OBRIGATORIA');

-- Inserir períodos de matrícula
INSERT INTO Periodo_Matricula (data_inicio, data_fim) VALUES
                                                          ('2024-07-01', '2024-07-30'),
                                                          ('2024-08-01', '2024-08-30');

-- Inserir matrículas
INSERT INTO Matricula (id_aluno, id_periodo) VALUES
                                                 (1, 1), -- João Silva no período 1
                                                 (4, 1), -- Ana Souza no período 1
                                                 (5, 2); -- Pedro Lima no período 2

-- Associar matrículas às disciplinas
INSERT INTO Matricula_Disciplina (id_matricula, id_disciplina) VALUES
                                                                   (1, 1), -- João Silva em Banco de Dados
                                                                   (2, 2), -- Ana Souza em Gestão Financeira
                                                                   (3, 3); -- Pedro Lima em Direito Penal

-- Inserir cobranças
INSERT INTO Cobranca (id_matricula, valor, data_vencimento) VALUES
                                                                (1, 500.00, '2025-04-08'),
                                                                (2, 400.00, '2025-04-08'),
                                                                (3, 600.00, '2025-04-08');

