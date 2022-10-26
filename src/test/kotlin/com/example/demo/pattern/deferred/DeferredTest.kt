package com.example.demo.pattern.deferred

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random

@SpringBootTest
class DeferredTest {
    @Test
    fun test() {
        runBlocking {
            val value = valueAsync()
            value.cancel()
            //println(value.await())
        }
    }
    suspend fun valueAsync(): Deferred<String> = coroutineScope {
        val deferred = CompletableDeferred<String>()
        launch {
            delay(100)
            if (Random.nextBoolean()) {
                deferred.complete("OK Deferred")
            }
            else {
                deferred.completeExceptionally(
                    RuntimeException()
                )
            }
        }
        deferred
    }

    @Test
    fun barrier_test() {
        runBlocking {
            val test = fetchFavoriteCharacter("easywaldo")
            println(test.name)
            println(test.catchphrase)
            println(test.picture)

            val (name, catchphrase, _) = fetchFavoriteCharacter("Inigo Montoya")
            println("$name says: $catchphrase")
        }
    }

    @Test
    fun each_async_coroutine_should_wait_all_task_completing_test() {
        runBlocking {
            val characters: List<Deferred<FavoriteCharacter>> = listOf(
                Me.getFavoriteCharacter(),
                Taylor.getFavoriteCharacter(),
                Michael.getFavoriteCharacter(),
            )
            println(characters.awaitAll())
        }
    }

    data class FavoriteCharacter(
        val name: String,
        val catchphrase: String,
        val picture: ByteArray = Random.nextBytes(42))

    fun CoroutineScope.getCatchphraseAsync(characterName: String) = async {
        "hello world"
    }
    fun CoroutineScope.getPicture(characterName: String) = async {
        byteArrayOf(1,2,3,4)
    }

    suspend fun fetchFavoriteCharacter(name: String) = coroutineScope {
        val catchphrase = getCatchphraseAsync(name)
        val picture = getPicture(name)
        FavoriteCharacter(name, catchphrase.await(), picture.await())
    }


    // barrier pattern sample
    object Michael {
        suspend fun getFavoriteCharacter() = coroutineScope {
            async {
                FavoriteCharacter("Terminator","Hasta la vista, baby")
            }
        }
    }
    object Taylor {
        suspend fun getFavoriteCharacter() = coroutineScope {
            async {
                FavoriteCharacter("Don Vito Corleone","I'm going to make him an offer he can't refuse")
            }
        }
    }
    object Me {
        suspend fun getFavoriteCharacter() = coroutineScope {
            async {
                // I already prepared the answer!
                FavoriteCharacter("Inigo Montoya","Hello, my name is...")
            }
        }
    }
}