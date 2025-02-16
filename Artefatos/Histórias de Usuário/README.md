# Sistema de Matrículas Universitário

## Funcionalidades do Sistema

- Autenticação e gerenciamento de usuários
- Matrícula e cancelamento de disciplinas
- Gerenciamento do currículo acadêmico
- Controle de disponibilidade de disciplinas
- Consulta de alunos matriculados
- Integração com o sistema de cobrança

---

## Histórias de Usuário

### Autenticação
| **Como** | aluno, professor ou secretário                                |
|----------|---------------------------------------------------------------|
| **Quero** | acessar o sistema usando minhas credenciais de acesso         |
| **Para que** | eu possa visualizar e gerenciar minhas informações acadêmicas |

**Critérios de Aceitação:**
- O usuário deve inserir login e senha válidos.
- O sistema deve validar as credenciais inseridas para liberação de acesso ao sistema.

---

### Efetuar Matrícula em Disciplinas
| **Como** | aluno |
|----------|--------------------------------------|
| **Quero** | me inscrever em disciplinas obrigatórias e optativas |
| **Para que** | eu possa cursá-las no próximo semestre |.

**Critérios de Aceitação:**
- O aluno pode escolher até 4 disciplinas obrigatórias e 2 optativas.
- A matrícula só será aceita se houver vagas disponíveis.
- A matrícula só pode ser feita dentro do período definido pela universidade.

---

### Cancelar Matrícula
| **Como** | aluno |
|----------|--------------------------------------|
| **Quero** | cancelar minha matrícula em uma disciplina |
| **Para que** | eu possa reorganizar minha grade de estudos antes do prazo final |

**Critérios de Aceitação:**
- O cancelamento só pode ser feito dentro do período de matrícula.
- Após o cancelamento, a vaga na disciplina fica disponível para outro aluno.

### Encerramento de Matrículas em Disciplinas Lotadas
| **Como** | aluno |
|----------|--------------------------------------|
| **Quero** | ser informado quando uma disciplina atingir o limite de vagas |
| **Para que** | eu possa escolher outra opção antes do encerramento da matrícula |

**Critérios de Aceitação:**
- O sistema exibe um aviso quando restam poucas vagas na disciplina.
- Quando a disciplina atinge 60 alunos, novas matrículas não são permitidas.

---

### Consultar Alunos Matriculados
| **Como** | professor |
|----------|--------------------------------------|
| **Quero** | visualizar a lista de alunos matriculados em minhas disciplinas |
| **Para que** | eu possa planejar minhas aulas e avaliações |

**Critérios de Aceitação:**
- O professor pode acessar a lista de alunos a qualquer momento.
- O sistema deve exibir nome, matrícula e e-mail dos alunos matriculados.
- A disciplina só será oferecida se tiver pelo menos 3 alunos matriculados.

---

### Gerenciar Currículo do Semestre
| **Como** | secretária                                                      |
|----------|-----------------------------------------------------------------|
| **Quero** | cadastrar e atualizar as disciplinas disponíveis a cada semestre |
| **Para que** | os alunos possam se matricular                     |


**Critérios de Aceitação:**
- O sistema permite adicionar, editar e remover disciplinas.
- Cada disciplina deve estar vinculada a um professor.
- O sistema deve validar se a disciplina atende aos critérios mínimos de alunos.

---

### Notificação do Sistema de Cobrança
| **Como** | sistema de matrículas |
|----------|-------------------------------------------------|
| **Quero** | notificar o sistema de cobrança após a matrícula do aluno |
| **Para que** | ele possa gerar a fatura correspondente |

**Critérios de Aceitação:**
- O sistema envia uma notificação automática após a matrícula ser confirmada.
- A cobrança deve ser gerada apenas para disciplinas efetivamente cursadas.