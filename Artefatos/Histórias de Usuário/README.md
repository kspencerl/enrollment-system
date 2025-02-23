# Sistema de Matrículas Universitário

## Funcionalidades do Sistema

- Autenticação de usuários,
- Gerenciamento de usuários (CRUD),
- Matrícula e cancelamento de disciplinas,
- Gerenciamento do currículo acadêmico,
- Controle de disponibilidade de disciplinas,
- Consulta de alunos matriculados,
- Integração com o sistema de cobrança.

---

## Histórias de Usuário

### Autenticação de Usuário
| **Como**      | aluno, professor ou secretário                                |
|---------------|---------------------------------------------------------------|
| **Quero**     | acessar o sistema usando minhas credenciais de acesso         |
| **Para que**  | eu possa visualizar e gerenciar minhas informações acadêmicas |

**Critérios de Aceitação:**
- O usuário deve inserir login e senha válidos, sendo ambos obrigatórios.
- O sistema deve validar as credenciais e permitir o acesso apenas para usuários cadastrados.
- Em caso de erro, o sistema deve exibir uma mensagem clara informando a falha na autenticação.

---

### Gerenciamento de Usuários (CRUD)
| **Como**      | secretário                                                     |
|---------------|----------------------------------------------------------------|
| **Quero**     | cadastrar, editar, visualizar e remover usuários do sistema    |
| **Para que**  | eu possa manter as informações dos usuários sempre atualizadas |

**Critérios de Aceitação:**
- O sistema deve permitir o cadastro de novos usuários, validando campos obrigatórios como nome, matrícula, função e e-mail.
- A edição e remoção de usuários devem ser restritas apenas a usuários com permissão de administrador.
- O sistema deve exibir uma lista completa dos usuários cadastrados, com opção de pesquisa por nome, matrícula ou função.

---

### Efetuar Matrícula em Disciplinas
| **Como**      | aluno                                                |
|---------------|------------------------------------------------------|
| **Quero**     | me inscrever em disciplinas obrigatórias e optativas |
| **Para que**  | eu possa cursá-las no próximo semestre               |

**Critérios de Aceitação:**
- O aluno pode escolher até 4 disciplinas obrigatórias e 2 optativas, respeitando esse limite.
- A matrícula só será aceita se houver vagas disponíveis na disciplina desejada.
- A matrícula deve ser realizada apenas dentro do período definido pela universidade, bloqueando inscrições fora do prazo.

---

### Cancelar Matrícula
| **Como**      | aluno                                                            |
|---------------|------------------------------------------------------------------|
| **Quero**     | cancelar minha matrícula em uma disciplina                       |
| **Para que**  | eu possa reorganizar minha grade de estudos antes do prazo final |

**Critérios de Aceitação:**
- O cancelamento só pode ser feito dentro do período de matrícula estabelecido.
- Após o cancelamento, a vaga na disciplina deve ser liberada para outros alunos interessados.
- O sistema deve confirmar o cancelamento através de uma mensagem clara ao usuário.

---

### Encerramento de Matrículas em Disciplinas Lotadas
| **Como**      | aluno                                                            |
|---------------|------------------------------------------------------------------|
| **Quero**     | ser informado quando uma disciplina atingir o limite de vagas    |
| **Para que**  | eu possa escolher outra opção antes do encerramento da matrícula |

**Critérios de Aceitação:**
- O sistema deve exibir um aviso quando restarem menos de 5 vagas na disciplina.
- Quando a disciplina atingir 60 alunos, novas matrículas devem ser automaticamente bloqueadas.
- O sistema deve sugerir disciplinas alternativas quando a escolhida estiver lotada.

---

### Consultar Alunos Matriculados
| **Como**      | professor                                                       |
|---------------|-----------------------------------------------------------------|
| **Quero**     | visualizar a lista de alunos matriculados em minhas disciplinas |
| **Para que**  | eu possa planejar minhas aulas e avaliações                     |

**Critérios de Aceitação:**
- O professor deve acessar a lista de alunos a qualquer momento através do sistema.
- O sistema deve exibir nome, matrícula e e-mail dos alunos de forma organizada e atualizada.
- A disciplina só deve ser oferecida se houver pelo menos 3 alunos matriculados.

---

### Gerenciar Currículo do Semestre
| **Como**      | secretária                                                       |
|---------------|------------------------------------------------------------------|
| **Quero**     | cadastrar e atualizar as disciplinas disponíveis a cada semestre |
| **Para que**  | os alunos possam se matricular                                   |

**Critérios de Aceitação:**
- O sistema deve permitir adicionar, editar e remover disciplinas de forma simples e eficiente.
- Cada disciplina deve estar obrigatoriamente vinculada a um professor responsável.
- O sistema deve validar se cada disciplina atende aos critérios mínimos de alunos antes de ser oferecida.

---

### Notificação do Sistema de Cobrança
| **Como**      | sistema de matrículas                                     |
|---------------|-----------------------------------------------------------|
| **Quero**     | notificar o sistema de cobrança após a matrícula do aluno |
| **Para que**  | ele possa gerar a fatura correspondente                   |

**Critérios de Aceitação:**
- O sistema deve enviar uma notificação automática apenas após a confirmação da matrícula.
- A cobrança deve ser gerada somente para disciplinas efetivamente cursadas pelo aluno.
- O sistema deve registrar a data e hora da notificação enviada ao sistema de cobrança.

