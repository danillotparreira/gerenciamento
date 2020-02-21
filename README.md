# Gerenciador

Este sistema será composto de Cadastro de usuário, perfil e restrições de acesso de acordo com o perfil.

O usuário logado terá como alterar seus dados cadastrais.

A classe encontrada na area de teste db/AdicionaUsuarioEPerfilDefault adiciona 2 Usuários e 2 perfis, sendo eles:
## Usuário

* Suporte: com login suporte e senha suporte
* Administrador: com login admin e senha admin
## Perfil:

* Sem permissão: não contém nenhuma permissão disponível.
* Administrador: Libera acesso a todas funcionalidades do sistema.
Podendo ser econtrado a classe neste link: https://github.com/danillotparreira/gerenciador/blob/master/src/test/java/br/com/danilloparreira/gerenciador/db/AdicionaUsuarioEPerfilDefault.java

Qualquer usuário que cadastre um novo usuário não poderá alterar a senha do mesmo, sendo de responsabilidade do usuário pedir para alterar a senha no login ao clicar no botão esqueceu a senha.

O suporte tem permissão para alterar a senha do usuário.
