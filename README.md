# Estagiei 🚀
Estagiei é uma plataforma web desenvolvida como parte de uma atividade acadêmica. Seu objetivo é facilitar a interação entre alunos, instituições de ensino e empresas, oferecendo uma solução integrada para o processo de contratação, acompanhamento, gestão de estágios e acesso à informações.


## 🕑Status
- Em desenvolvimento


## 📋 Funcionalidades
- Cadastro de alunos, empresas e instituições de ensino
- Gestão de estágios:
  - Previsão e notificações de término de contrato
  - Opções para extensão ou encerramento do contrato
- Gerenciamento de arquivos:
  - Upload de modelos de relatório de estágio pela instituição
  - Upload de relatórios preenchidos pelos alunos
  - Download de relatórios por usuários associados ao estágio
- Perfis personalizados: cada usuário pode personalizar seu perfil com fotos, banner, informações de contato e habilidades profissionais


## ⚙️ Tecnologias Utilizadas
Backend:
- Java 17
- Spring Boot 3.4.1
- Spring MVC
- JPA
  
Banco de Dados:
- MySQL

Frontend:
- Thymeleaf
- Bootstrap

Ambiente de Desenvolvimento:
- NetBeans IDE


## 🛠️ Requisitos do Sistema
Requisitos funcionais:
- [RF001] Cadastro de Usuários: Alunos, instituições e empresas podem se cadastrar na plataforma, com validações específicas para cada tipo de usuário.
- [RF002] Login: Autenticação com email ou documento usado no cadastro.
- [RF003] Upload de Arquivos: Instituições podem disponibilizar modelos de relatórios e alunos podem enviar relatórios preenchidos.
- [RF004] Download de Arquivos: Relatórios preenchidos ficam disponíveis para download por usuários associados.
- [RF005] Personalização de Perfis: Personalização de perfis com fotos, banners e informações de contato.
- [RF006] Encerramento de Contratos: Empresas podem encerrar contratos, notificando outras partes e desabilitando o perfil do aluno.

Requisitos não funcionais
- [RNF001] Sessão: Usuários são desconectados após 15 minutos de inatividade.
- [RNF002] Acessibilidade: Design acessível com suporte a diferentes dispositivos e necessidades especiais.
- [RNF003] Tutorial: Tutorial de primeiro acesso para novos usuários.
- [RNF004] Segurança: Bloqueio temporário após 3 tentativas de login incorretas.
- [RNF005] Responsividade: Acesso otimizado para dispositivos móveis e desktops.
- [RNF006] Atualizações em tempo real: Informações atualizadas instantaneamente no sistema.


## 🧑‍💻 Equipe
- Desenvolvimento: Natali Schers
