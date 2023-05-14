# sb-html-to-pdf
## Laboratório de conversão de HTML para PDF

Este é um laboratório simples para converter um arquivo HTML em um arquivo PDF e armazenar o resultado em uma base de dados OracleXE.

## Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:

- Java 8
- Docker Compose
- Maven 3

## Executando o projeto

Para executar o projeto, siga os passos abaixo:

1. Inicialize o banco de dados OracleXE usando o Docker Compose:
```shell
sudo docker compose up --remove-orphans
```
ou
```shell
sudo docker-compose up --remove-orphans
```

2. Execute o projeto usando o Maven:
```shell
mvn clean spring-boot:run
```

O serviço será iniciado na porta 8080.

## API

### POST /html-to-pdf/api/documento

Converte o conteúdo HTML fornecido em um arquivo PDF e armazena o resultado em uma base de dados OracleXE.

#### Corpo da solicitação

Exemplo de corpo da solicitação:

```json
{
  "fileName": "document.pdf",
  "documentPDF": "PGgxPmhlbGxvIHdvcmxkPC9oMT4NCjxwPkNvbnRyYXJ5IHRvIHBvcHVsYXIgYmVsaWVmLCBMb3JlbSBJcHN1bSBpcyBub3Qgc2ltcGx5IHJhbmRvbSB0ZXh0LiBJdCBoYXMgcm9vdHMgaW4gYSBwaWVjZSBvZiBjbGFzc2ljYWwgTGF0aW4gbGl0ZXJhdHVyZSBmcm9tIDQ1IEJDLCBtYWtpbmcgaXQgb3ZlciAyMDAwIHllYXJzIG9sZC48L3A+"
}
```

### Teste

Você pode testar a conversão de HTML para PDF e o armazenamento em base64 no banco de dados OracleXE usando o seguinte comando na raiz do projeto:

```shell
curl -X POST "http://localhost:8080/html-to-pdf/api/documento" -H "Content-Type: application/json" -d"@src/test/resources/post-documento.json" | jq 
```

O comando acima envia um arquivo JSON localizado em `src/test/resources/post-documento.json` contendo o conteúdo HTML em base64 para a API `documento` usando o método POST. O comando `jq` é usado para formatar a saída JSON da API. O resultado deve ser um JSON vazio indicando que a conversão foi bem-sucedida e que o PDF foi salvo em base64 no banco de dados.

Certifique-se de ter o `curl` e o `jq` instalados em seu sistema antes de executar este comando de teste. O arquivo `post-documento.json` deve estar localizado no diretório `src/test/resources` do projeto.
