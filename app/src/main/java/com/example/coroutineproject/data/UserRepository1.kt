package com.example.coroutineproject.data

import com.example.coroutineproject.api.UserService
import com.example.coroutineproject.model.UserModelItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserRepository1 @Inject constructor(private val userService: UserService) {
    suspend fun getPosts() :Flow<List<UserModelItem>> = flow {
        val res = userService.getPosts()
        emit(res)
    }
}












fun main1() = runBlocking {
    launch {
        delay(1000L)
        println("Hello, World!")
    }
    getPosts()
    println("Hello from runBlocking")
}

fun main()= runBlocking {

    (1..10).asFlow().map {
        runningTestFlow(it)
    }.collect {
        println("response is $it")
        // this place will catch repose from runningTestFlow.
    }

}

suspend fun runningTestFlow(valueOfString:Int):String{
    return "Jupyter$valueOfString"
}
suspend fun getPosts() = coroutineScope {

    val job = launch {
        delay(5000L)
        println("Hello, World!")
    }
    println("Hello from coroutineScope")
    job.cancel()    // for kotlin coroutines cancellation of job
    job.join()   // for kotlin coroutines join of job
    job.cancelAndJoin() // for kotlin coroutines cancellation and join of job
    println("Hello from coroutineScope after job successful")
}
fun sumNumber(a: Int, b: Int): Flow<Int> = flow {
    val res = a+ b;
    for(i in 1..res) {
        delay(1000L)
        emit(i)
    }
    emit(res)
}.flowOn(Dispatchers.Default)
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}
