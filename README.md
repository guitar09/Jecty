# Jecty

Jecty é um dependence injection simples criado para fins de estudo.

Para obter uma dependência, você precisa utilizar a função ```get()``` que pode aceitar um qualifier como parâmetro, por exemplo: ```get("mock)```.

<h2>Configuração</h2>

Você precisa de uma classe selada (sealed class) herdando de JectyQualifier. 

```kotlin
sealed class In(override val clazz: KClass<*>, override val named: String? = null) :
    JectyQualifier(clazz, named)
```
    
Depois, você precisa criar alias para suas dependências:
```kotlin
object ApiJect : In(Api::class)
object RepositoryJect : In(Repository::class)
object UseCaseJect : In(UseCaseSentence::class)
object SessionJect : In(Session::class)
```

Se necessário, você pode passar um qualifier como parâmetro:
```kotlin
object SessionJect : In(Session::class, "seu_qualifier")
```
Após isso, é necessário configurar seu Module implementando a interface ```JectyModule``` e passando sua classe selada (sealed class) como parâmetro:
```kotlin
class Module (private val session: Session) : JectyModule<In> {
    override fun configInjectionTree(clazz: In): Any {
        return when (clazz) {
            ApiJect -> ApiImpl()
            RepositoryJect -> RepositoryImpl(get())
            UseCaseJect -> UseCaseSentence(get())
            SessionJect -> session
        }
    }

    override fun clazz(): KClass<In> = In::class
}
```
No retorno da função ```configInjectionTree```, você precisa criar os factory das suas dependências.

<h2>Inicializando o Jecty</h2>

```kotlin
  startJecty {
    module = Module() //você deve passar seu Module aqui
    checkTree = true|false // Você pode verificar a árvore de dependências declaradas após inicializar a biblioteca.
  }
```

<h2>Testes</h2>

Para mockar uma dependência em seus testes, você pode usar a função ```injectMockTest```, por exemplo:
```kotlin
val api : Api = myCustomMock()
injectMockTest<Api>(api)
```

Você pode criar um teste para validar árvore de dependências declarada, chamando a função
```kotlin
@Test
fun checkTree() {
    checkJectyTree()
}
```

