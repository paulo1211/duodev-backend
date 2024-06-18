Como rodar o projeto
=============

O projeto foi pensado para rodar no docker seja localmente ou em um servidor.
-------------

  Para rodar o projeto, basta rodar no terminal do computador e dentro da pasta do projeto:
  ```sh
  docker-compose -f nome-do-arquivo.yml up -d
  ```
ou
  ```sh
  docker compose -f nome-do-arquivo.yml
  ```
  Depende da versão do seu docker compose, se for v2 usar o com traço, se for v3 usar a com espaço.
  
  Ao rodar o docker-compose-test.yml será iniciado um banco de dados postgres localmente (ajustar as variáveis de ambiente do projeto para as do banco local).
  Caso seja escolhido o docker-compose-prod.yml, será iniciado somente o backend. Ambos os backends rodam na porta padrão 8080.

  
