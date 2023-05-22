
# Desafio técnico para Desenvolvedor(a) Android.

## Regras Gerais

As regras são as seguintes:

- O candidato tem até 3 dias para entregar a implementação do desafio.
- Nenhuma alteração no projeto será permitida após a entrega, caso contrário o candidato será desclassificado.
- O projeto deve ser desenvolvido em Android navito (Kotlin).
- O projeto deve ser disponibilizado em um repositório git (Github, Bitbucket ou Gitlab) público.
- O projeto deve conter as instruções necessárias para realizar a execução do mesmo.
- O candidato pode utilizar bibliotecas open-source de terceiros.

## Desafio

O desafio consiste na implementação de uma aplicação Android que deverá consumir a API pública do GitHub.

A API disponibiliza informações sobre os usuários e seus repositórios.

URL da API: https://api.github.com

O aplicativo deverá permitir:
- listagem de usuários;
- busca de usuário por nome de usuário;
- visualização das informações de um usuário específico;
- e a listagem de seus repositórios.

## Descrição

A aplicação deverá consumir um serviço para realizar tais operações:

- Este endereço lista apenas alguns usuários. Isto pode servir como uma massa de dados para a tela de listagem dos usuários da aplicação.
Para listar os usuários, a aplicação deverá consumir o seguinte endereço:<br/>
https://api.github.com/users

- Para obter informações específicas de um usuário, basta acessar o seguinte endereço:<br/>
https://api.github.com/users/torvalds

- Para listar os repositórios de um usuário específico, a aplicação deverá acessar o seguinte endereço:<br/>
https://api.github.com/users/torvalds/repos

- Para obter mais informações sobre a API:<br/>
https://developer.github.com/v3/

## Dicas

É sempre bom avisar ao usuário quando uma operação está em andamento.

A API pode retornar erros, por isso pense em como apresentar isso ao usuário.

Faça testes automatizados.

Explore a API antes de começar qualquer coisa.

Tire um tempo para entender tudo e faça um planejamento. Um bom projeto é fruto de um bom planejamento

## Funcionamento

A primeira tela do Aplicativo é possivel ver a logo do github, um espaço para pesquisas e uma lista com usuários e seus repositorios.

<img src="img1.jpeg" width="200">

Ao digitar um nome e buscar a tela atualiza mostrando o usuário caso este esteja com uma conta no github.

<img src="img2.jpeg" width="200">

Por fim pode-se clicar no usuário da pequisa ou nos da tela inicial para poder acessar a tela de detalhes do usuário, com seus repositories e informações

<img src="img3.jpeg" width="200">


