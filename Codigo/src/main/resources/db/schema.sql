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
DROP TABLE IF EXISTS Professor_Disciplina CASCADE;
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
                       id SERIAL PRIMARY KEY,
                       id_usuario INT NOT NULL UNIQUE,
                       id_curso INT NOT NULL,
                       FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
                       FOREIGN KEY (id_curso) REFERENCES Curso(id) ON DELETE CASCADE
);

CREATE TABLE Professor (
                           id SERIAL PRIMARY KEY,
                           id_usuario INT NOT NULL UNIQUE,
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
INSERT INTO Disciplina (nome, id_curso, creditos, categoria) VALUES
                                                                 ('Banco de Dados', 1, 4, 'OBRIGATORIA'),
                                                                 ('Programação Web', 2, 5, 'OBRIGATORIA'),
                                                                 ('Inteligência Artificial', 3, 4, 'OPTATIVA'),
                                                                 ('Computação em Nuvem', 4, 3, 'OPTATIVA');

-- Associar professores às disciplinas
INSERT INTO Professor_Disciplina (id_professor, id_disciplina) VALUES
                                                                   (1, 1), -- Maria Oliveira -> Banco de Dados
                                                                   (2, 2), -- Ricardo Mendes -> Programação Web
                                                                   (3, 3); -- Juliana Costa -> Inteligência Artificial

-- Inserir períodos de matrícula
INSERT INTO Periodo_Matricula (data_inicio, data_fim) VALUES
                                                          ('2024-01-01', '2024-02-01'),
                                                          ('2024-07-01', '2024-08-01');

-- Inserir matrículas
INSERT INTO Matricula (id_aluno, id_disciplina, id_periodo) VALUES
                                                                (1, 1, 1), -- João em Banco de Dados
                                                                (2, 2, 1), -- Ana em Programação Web
                                                                (3, 3, 2), -- Pedro em IA
                                                                (4, 4, 2); -- Fernanda em Computação em Nuvem

-- Inserir cobranças
INSERT INTO Cobranca (id_matricula, valor) VALUES
                                               (1, 1500.00),
                                               (2, 1200.00),
                                               (3, 1800.00),
                                               (4, 1600.00);
