## jwt-validator

Este é um projeto de aplicação que expõe uma API web capaz de receber um JWT (JSON Web Token) como parâmetro e verificar sua validade.

### Instruções para executar o projeto:

1. Clone o projeto.
2. Instale as dependências listadas no arquivo `pom.xml`.
3. Execute a classe `JwtValidatorApplication`.
4. Utilize sua ferramenta de teste de API:
   - Use o método GET `/main/greetings`. Isso retornará um "Hello World!" para confirmar que a API está funcionando corretamente.
   - Para criar um JWT, utilize o método POST `/main/createjwt`, passando o JSON do payload JWT no corpo da requisição.
   - Para testar um JWT, utilize o método GET `/main/validatejwt`, passando o JWT criado como parâmetro no parâmetro "jwt". Você receberá uma resposta de acordo com a validade do JWT.

### Explicando os principais métodos da classe `JWTValidatorService`:

- **createJwt**: Este método utiliza o payload passado como parâmetro no corpo da requisição `/main/createjwt` para criar um JWT. Ele utiliza o algoritmo de criptografia HS256 e a chave de assinatura definida em `application.properties` na variável `jwt.secret`. Mesmo com um payload inválido, o método criará um JWT, porém um payload inválido resultará em um JWT inválido, que gerará uma resposta "false" no método `/main/validatejwt`.
- **loadAllValidations**: Este método funciona com o JWT passado como parâmetro em `/main/validatejwt`. Ele analisa o JWT verificando primeiro se é válido e, em seguida, realiza várias validações.

### Métodos de verificação do JWT:

O projeto verifica o JWT das seguintes maneiras:

- Verifica se o JWT é válido, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, as informações contidas atendem à descrição." ou "Justificativa: JWT inválido."
- Verifica se a claim "Name" do JWT está vazia, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, a Claim Name é vazia."
- Verifica se a claim "Name" do JWT contém números, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, a Claim Name possui caracteres numéricos."
- Verifica se a claim "Name" do JWT possui mais de 256 caracteres, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, a Claim Name possui mais de 256 caracteres."
- Verifica se o JWT possui todas as 3 claims necessárias, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, não foram encontradas todas as claims necessárias."
- Verifica se o JWT possui mais ou menos de 3 claims, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, há mais/menos de 3 claims."
- Verifica se a claim "Role" do JWT está vazia, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, a Claim Role é vazia."
- Verifica se a claim "Role" do JWT possui um dos três valores necessários ("Admin", "Member" ou "External"), retornando a resposta correspondente: "Justificativa: Abrindo o JWT, a Claim Role não possui um dos três valores válidos (Admin, Member e External)."
- Verifica se a claim "Seed" do JWT é um número primo, retornando a resposta correspondente: "Justificativa: Abrindo o JWT, a Claim Seed não é um número primo."
