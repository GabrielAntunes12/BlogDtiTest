# BlogDtiTest
Esse aplicativo foi desenvolvido a fins de concluir um teste exigido.

### Instrução para execução e utilização do projeto.
1. Primeiro passo, caso builde e execute o app em seu device/emulador, terá uma tela onde o usúario terá que escolher entre utilizar a API MOCKADA, ou a API REST desenvolvida em GoLang que está na pasta API.
 - Caso a escolha for a API em GoLang, ler o readme dentro da pasta API, para executar a aplicação do Go no localhost para a API estar disponivel em seu ambiente, para prosseguir com o fluxo no app.
 - Caso a escolha for a API Mockada, continuar normalmente no fluxo do app.
 - Sempre que o usúario desejar trocar de API, é necessario matar o APP e abri-lo, foi retirada a tela inicial de decisão da pilha, para o usúario ter uma experiencia apenas do blog, pois a tela é um plus para o teste.
 - Quando tiver alteração por escolha do usúario, se criar uma lista da api mockada, sempre quando fechar e abrir o app e a escolha for mockada, a lista permanecerá, mas a partir do momento onde é clicada para a API Rest, o mockado sumirá,paara abrir oportunidades para testes "apagando" os posts existentes (Existia a possibilidade de deixar a lista do mockado não ser apagada, pois ambas APIs não tem relação e estão separadas em código e responsabilidades distintas.).
 - Enquanto a API REST estiver rodando em seu localhost, os dados não serão afetados e quaisquer criação se manterá até matar o processo do localhost.

### Utilização e Funcionalidades do APP
  - Tela inicial com uma lista dinâmica  que possui rolagem caso tenha itens que não caibam na tela.
  - Cada item possui o título e data criada podendo identificar a diferença das postagens do blog.
  - Clicando em cada post, você consegue abrir os detalhes e ler o texto/descrição que foi escrito no post.
  - Adicionar novos posts e fazendo eles aparecerem na sua lista dinâmica .

## Decisões Tecnicas e Detalhes
1. Arquitetura
    - A arquitetura utilizada foi a MVVM com conceitos de MVI.
    - Os conceitos de MVVM podem ser encontrados com as criações de ViewModels e controle sobre as Views, juntamente com o responsável por gerir dados o Repositorio, optando não adicionar "UseCases" mesmo que seria interessante para a decisão de escolher entre as APIs Mockadas ou REST, porém por ser um projeto pequeno, não atrapalharia tanto na visualização da manutenção.
    - Os conceitos do MVI, foram abstraidos a ponto de STATES de tela utilizando LiveData com sealedclass para a passagem de estado para as views, assim tendo um código mais limpo nas VMs e podendo gerenciar melhor as responsabilidades da view.
2. XML
   - A Escolha do xml se trata por ser um projeto com poucas telas e uniforme.
3. Jetpack Android
   - Foram utilizados alguns componentes do jetpack, como ViewBinding, AppCompat, optando também pela não utilização de Fragment, Navigation e ROOM, pelo tamanho do projeto a ser entregue.
4. Tratamento de erro
   - Os tratamentos de erros estão implementados no código para o melhor uso do usuario e a não quebra de experiencia. Tratamentos como de API não estão sendo detalhadas a nivel de code response (404, 401, 500...).
5. Caso de Testes
   - Um teste unitario para testar uma função de formatar data no utils.
6. Gradle
   - Gradle está em Kts, devido o suporte recente da google e com as possibilidades de programação oferecidas por eles.
   - Versões de dependencias em um script separado para melhor troca e visualização.
