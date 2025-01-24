#   Primeiro Curso da formação Spring Boot

#   Objetivos

*   Desenvolvimento de uma API Rest
*   CRUD (Create, Read, Update, Delete)
*   Validações -> Validações das informações que chegam na API, utilizando o bin validation
*   Paginações e ordenação de informações que a API devolve

#   TECNOLOGIAS

*   Spring Boot 3
*   Java 21
*   Lombok -> Faz geração de código repetitivos, como getter e setters, to string
*   MySql -> Para armazenar informações na API
*   Flyway -> Controle de versionamento das migrations
*   JPA/Hibernate -> Camada de persistência da aplicação, com o Hibernate como implementação dessa especificação
*   Maven -> Ferramenta de gerenciamento de dependências, e que gera o build da aplicação
*   Insomnia -> Plataforma para visualizar o backend

#   Abrir o spring initlz:
*   Java
*   Maven
*   Jar

#   Dependencies
*   SpringBoot DevTools
*   Lombok
*   Spring Web

#   Com o projeto aberto na IDE

*   O Spring Boot não vem dentro da tag dependencies, ele vem dentro da tag parent, que herda do pom.xml do spring boot
*   Em application é onde é rodado o projeto.
*   Em resourses/static é onde fica o front end, como css
*   Templates, é onde ficam as páginas html 
*   application.properties -> Configurações do projeto Spring Boot

*   O Spring Boot é capaz de ter o servidor TOMCAT imbutido dentro da aplicação. Sem o Spring Boot seria necessário criar um servidor próprio TOMCAT para então a aplicação ir dentro do servidor. Ja com o Spring Boot, é o servidor que vai dentro da aplicação.

## Classe ApimarketplaceApplication.java

*   Essa classe possui a notação @SpringBootAplication que tem um método main que vai puxar o método run da classe SpringApplication que por fim rodará o projeto, lendo todas as classes e arquivos.

package marketplace.com.apimarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApimarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApimarketplaceApplication.class, args);

	}

}

##  Criando a classe de teste HelloController

*   No pacote marketplace.com.apimarketplace, criar o subpacote controller e criar a classe HelloController.
*   Na classe HelloController inserir a anotação do Spring MVC @RestController e @RequestMapping.
*   Na classe criar o método dizerOlá, esse método precisa devolver uma String, e o método tem que ter a anotação @GetMapping

* IMPORTANTE : Para o devtools funcionar corretamente no intellij, é preciso fazer uma configuração. No compiler, adicionar build execution project.

##  Enviando dados para API - Parte 1

*   Abrir o insomnia, criar um novo projeto chamado API Marketplace, criar uma coleção de requisição, e criar uma requisição chamada Cadastro de loja com o método POST. Inserir a url http://localhost:8080/lojas
*   Para enviar os dados, coloque os dados no json.
*   Voltar no Intellij para criar o controller LojaController dentro do pacote controller
*   Inserir a anotação @RestController, para chamar o spring framework
*   Inserir a anotação @RequestMapping("/lojas"), para mapear o endereco para disparar a requisição na API
*   Criar o método public void cadastrar(@RequestBody String json){ -> O parâmetro recebe os dados que estão vindo na requisição, porém esse parâmetro String json tem que pegar do corpo da requisição
        System.out.println(json);
}

## Enviando dados para API - Parte 2 (DTO)

* No método cadastrar trocar o parâmetro String json, pelo DTO (Record- DadosCadastroLoja), cria a record dentro do pacote loja.
* Na record DadosCadastroLoja, chamar os dados como parametro, Criar um ENUM para nicho (dentro do pacote loja) e criar um outro DTO para endereco, dentro do pacote endereco e chamar os dados.
* DTO - Data Transfer Object, é muito utilizado para representar dados que chegam na API e dados que são devolvidos da API
*  O próximo passo agora é fazer salvar os dados de DadosCadastroLoja em um banco de dados

## Adicionando dependências

* Entrar no initzl, pesquisar as dependências, validation, MySQL driver, Spring data JPA, Flyway
* Copiar as tags , dependencies no explore e colar no pom.xml
* Fazer as configurações no arquivo application.properties. Adicionar a url de conexão com o banco de dados , login e a senha do banco de dados. 
* Inserir spring.datasource.url=jdbc:mysql://localhost/apimarketplace, spring.datasource.username=root, spring.datasource.password=root

## Entidades JPA

*  Criar a classe Loja, no pacote loja e inserir os mesmos atributos que estavam na record. Criar a classe Endereco e inserir os mesmos atributos que estavam na recor dadosEndereco.
*  Para ser uma entidade JPA, é preciso escrever as anotações.
*  @Table, @Entity, @Id @GeneratedValue, @Enumerated, @Embedded na classe loja, e na classe endereco ter @Embedable
*  Utilizar o Lombok para gerar os getter e setters -> Utilizar as anotações na Entidade Loja : @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHasCode(of = "id"). Fazer a mesma coisa na classe Endereco, só tirar o EqualsAndHashCode
*  Com isso, a entidade Loja já está mapeada. Mas para salvar isso no banco de dados é preciso utilizar as interfaces Repository, que o Spring oferece a implementação.

## Interface Repository

* Criar uma interface LojaRepository no pacote Loja
* LojaRepository fica -> public interface LojaRepository extends JpaRepository<Loja, Long>{}
*  No generics, Loja é a entidade e Long é o tipo do atributo da chave primária.
*  Com a interface Repository criada, na LojaController, inserir @Autowired private LojaRepository repository . Essa anotação vai fazer com o que o Spring instancie a classe LojaRepository
*  No método cadastrar chamar repository.save(new Loja(loja))  criar o construtor na classe entidade loja, e também criar o construtor que recebe dados da loja em endereco

## Migrations Flyway

* Com todo o mapeamento feito para cadastro de lojas, agora é preciso utilizar as migrations
* O flyway permite fazer o controle de versionamento do banco de dados. O banco de dados evolui, sempre há alterações então é preciso ter o histórico de evolução. A ferramenta de migração existe pra isso, e no spring boot está adicionado o flyway.
* Passo a passo para criar as migrations
  1. Dentro da pasta resources criar db/migration.
  2. Lembrar de toda vez que for mecher em migrations, parar o projeto. Porque utilizando o modo dev tools, ele vai salvar automaticamente um script por exemplo e vai atualizar o flyway que pode dar problemas com migration
  3. Dentro do diretório db.migration criar o arquivo com a seguinte nomenclatura que o flyway segue como padrão : V1__create-table-lojas.sql
  4. Mas antes o projeto no intellij precisa estar conectado ao data source. E também precisa existir o banco de dados para quando for criado a migration, der certo de criar a tabela no db.
  5. Lembrar de colocar a anotação @Transactional na classe Loja Controller, pois como o método cadastrar faz um insert no banco, ou seja uma escrita, precisa ter essa anotação.
     
## Fazendo validações

*   Validar as informações que estão chegando na API, será que as informações estão vindo corretamente ? tem campo obrigatório ?
*   Utilizar o BeanValidation na record DadosCadastroLoj com anotações.
*   @NotNull -> Quando o campo é obrigatório, não pode ser nulo. @NotBlank -> Verifica se o campo não está vazio, @Email -> próprio para emails, @Pattern(regexp = "\\d{4,6}") -> Sifnifica que o campo tem entre 4 e 6 dígitos, @Valid -> Valida um objeto que está como atributo

## Adicionando novo campo Nome do proprietário 

*   Adicionar no DTO dadosCadastroLoja, na entidade Loja (atributo e construtor)
*   Criar alter table, V2__alter-table-lojas-add-column-nomeproprietario.sql
*   No arquivo inserir o comando sql : alter table lojas add nomeproprietario varchar(20) not null;
*   As migrations são imutáveis, cada mudança deve ser feita criando uma nova migration.

##  Começando a listagem de lojas

*   Abrir a classe LojaController e criar o método listar. Esse método devolve uma lista do DTO DadosListagemLoja. o Código fica
*   Na classe LojaController :
    public List<DadosListagemLoja> listar(){
        return repository.findAll()
    }

*   Na record DTO DadosListagemLoja -> Precisa ter os atributos que está pedindo pra devolver.

*  Na classe LojaController converter a entidade Loja para DadosListagemLoja fazendo um mapeamento com o construtor de DadosListagemLoja que recebe um objeto do tipo Loja
*  Ir no insomnia para criar uma nova requisicao, GET
*  GET http://localhost:8080/lojas

## Paginação

*  Adicionar Page ao invés de List. Adicionar o parametro paginacao do tipo Pageable. Tirar o toList(), adicionar save(paginacao) como sobrecarga.
*  Então com isso, além do Spring devolver os dados cadastrados, devolve também as informações de paginação.
*  Para controlar a quantidade que quer devolver de informação cadastrada :
   Modificar a URL no insomnia
   http://localhost:8080/lojas?size=1 -> A quantidade de usuário cadastrado
   http://localhost:8080/lojas?size=1&page=1 -> A página ordenada de usuário

##  Ordenação

*   Como mudar a ordem em que estão cadastrados
    http://localhost:8080/lojas?sort=nome Por padrão é de maneira crescente
*   Para mudar para decrescente
    http://localhost:8080/lojas?sort=nome,desc

## Atualização de Lojas

*   Atualizar os dados especificados
*   Pra cada cadastro é importante enviar o ID também. Pra isso, inserir Long id no DTO DadosListagemLoja
*   No insomnia criar uma nova requisição com o nome de Atualizar Lojas, e criar o body em json.
*   Fazer o método atualizar com a anotação @PutMapping e @Transactional, porque estará fazendo uma escrita no db
*   No método atualizarLoja, criar  fazer o o bjeto loja acessar o banco de dados e localizar o id , pra isso :
    var loja = repository.getReferenceById(dados.id()), ou seja carrega o objeto loja que está chegando no DTO

##  Fazer Exclusão de Lojas

*   Não vai apagar o registro do banco de dados, apenas deixará inativo
*   É preciso levar o id da loja para identificar qual das lojas será apagada.
*   Adicionar o id da loja , parametro de url, faz parte da url http://localhost:8080/lojas/1
*   Adicionar o método excluir na classe LojaController,e anotação @DeleteMapping adicionar o parâmetro dinâmico ("/{id}")
*   Adicionar anotação @Transactional

##  Fazendo exclusão lógica

*   Não vai apagar o registro do banco de dados, vai apenas deixá-lo como ativo
*   Criar mais uma migration com o nome de V3__alter-table-lojas-add-column-ativo.sql
*   Nessa tabela inserir os comando alter table add ativo tinyint; e update lojas set ativo = 1;
*   Criar novo atributo na entidade Loja -> private Boolen ativo
*   Atualizar o construtor da entidade Loja -> this.ativo = true;
*   Lá no controler , carregar pelo id -> var loja = repository.getReferenceById(id);
*   Ainda na controller chamar o método excluir -> loja.excluir()
*   Criar o método excluir em Loja, colocar this.ativo = false

##  Atualizando a listagem

@GetMapping
public Page<DadosListagemLoja> listar( Pageable paginacao){
return repository.findAllByAtivoTrue(paginacao).map(DadosListagemLoja::new);
}

*   Em LojaRepository, adicionar Page<Loja> findAllByAtivoTrue(Pageable paginacao);


#   Segundo Curso da Formação Spring Boot

##  Objetivos :
*   Boas práticas na API em relação ao protocolo http. Quanto ao retorno e respostas que api edvolve
*   Tratamento de erros, exceptions.
*   Segurança, controle de autenticação e autorização da API
*   Autenticação baseada em Tokens com padrões web token JWT

##  Padronizando retornos da API

### Classe LojaControler - Método excluir

*   Ver que os métodos estão devolvendo void da classe LojaController e devolve o código 200. Precisa devolver o código 204. A requisição 204 não tem um conteúdo, não tem um corpo
*   Começando pelo método excluir.
*   Utilizar uma classe do próprio spring chamado ResponseEntity
*   Colocar return e instanciar o objeto ResponseEntity. Nessa classe existe vários métodos, escolher o noContent() que vai criar o objeto, e tem que chamar o build() para construir o objeto
*   return ResponseEntity.noContent().build(); 
*   Voltar no insomnia para disparar a requisição DELETE e ver se devolve requisição 204.
*   Adicionar a classe ResponseEntity em todos os métodos.
* 
### Classe LojaControler - Método listar

*   No método listar , criar uma variável page para o repository
*   E colocar o código return ResponseEntity.ok(page). Então no método listar vai devolver o código 200 

### Classe LojaControler - Método atualizar

*   No método atualizar , tem que devolver o objeto a informação que foi atualizada. Então devolve os dados da loja atualizados
*   return ResponseEntity.ok(DTO)
*   return ResponseEntity.ok(new DadosAtualizacaoTotalLoja(loja));
*   Criar a record no pacote loja, e inserir as informações da loja
*   Ainda na DTO adicionar o construtor que recebe objeto loja
*   public DadosDetalhamentoLoja(Loja loja){
    this(loja.getId(), loja.getNome(), loja.getEmail(), loja.getCnpj(), loja.getNomeproprietario, loja.getNicho, loja.getEndereco())

##  Devolvendo código HTTP 201

### No método cadastrar 

*   Para entender melhor, quando é feito o cadastro na API, o método devolve o código 201 (created), que representa que o registro foi criado na api, que no corpo da resposta deve ser devolvido os dados do registro e devolve um cabeçalho do protocolo http (endereco pro front end acessar o que foi cadastrado)
*   Para criar o objeto response entity, no método adicionar -> return ResponseEntity.created(uri).body(new DadosDetalhamentoLoja(loja));
*   O Spring possui uma classe para trabalhar com uri, então como parametro do método cadastrar, adicionar UriComponentsBuilder uriBuilder
*   Acima da linha do return adicionar -> var uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
*   Modificar o código para -> var loja = new Loja(loja)
                               repository.save(loja)

##  Detalhando dados de loja na API

*   Criar uma nova requisição no insomnia do tipo GET (detalhamento dados)
*   Criar método detalhar :
    @GetMapping("/{id}")
    public ResponseEntity detalharLoja(@PathVariable Long id){
        var loja = repository.getReferenceById(id)
        return ResponseEntity.ok(new DadosAtualizacaoTtoalLoja(loja))
    }

##  Lidando com erros

### Erro 404

*   Caso enviar requisição para um id que não existe o erro é 500, erro interno do servidor da api backend.
*   É preciso tratar esses erros.
*   Ler documentação spring boot application properties.
*   Adicionar no application.properties - server.error.include-stacktrace=never
*   Criar uma classe para devolver o erro 404
*   Inserir os pacotes loja, cliente e controller dentro de um pacote chamado domain.
  *   Criar um novo pacote chamado infra e dentro desse pacote criar a classe tratadorDeErros
      Na classe tratadorDeErros :
          @RestControllerAdvice, é uma anotação específica para tratamento de erros.
                                                        @ExceptionHandler(EntityNotFoundException.class) -> Em qualquer controller se for lançada essa exception da entity not found, chama o método tratarErro404
          Criar método para EntityNotFound Exception. -> public ResponseEntity tratarErro404{
                                                            return ResponseEntity.notFound().build();
                                                        }
  *  Com isso feito, a mensagem de erro não será mais tratada pelo spring e sim será tradada pelo classe TratadorDeErros

### Erro 400

*   Dados incorretos de validação
*   Criar método tratarErro 400
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(){
        return ResponseEntity.badRequest().build()
    }


