package com.example.flowbasics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.whileSelect

class MainViewModel: ViewModel() {
    val countDownFlow = flow<Int> {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while(currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
//            val flow1 = flow {
//                emit(1)
//                delay(500L)
//                emit(2)
//            }
//            viewModelScope.launch {
//                flow1.flatMapConcat { value ->
//                    flow {
//                        emit(value + 1)
//                        delay(500L)
//                        emit(value + 2)
//                    }
//                }.collect { value ->
//                    println("The value is $value")
//                }
//            }

            val flow1 = flow {
                delay(250L)
                emit("Appetizer")
                delay(1000L)
                emit("Main dish")
                delay(100L)
                emit("Dessert")
            }
            viewModelScope.launch {
                flow1.onEach {
                    println("FLOW: $it is delivered")
                }
//                    .buffer()     //дозволяє flow1 продовжувати роботу навіть якщо .collect не закінчив
//                    .conflate()   // з .collectLatest дозволяє скіпати закінчення роботи
//                                  // .collectLatest якщо вже почав працювати flow1
                    .collect {
                         println("FLOW: now eating $it")
                        delay(1500L)
                        println("FLOW: finished eating $it")
                    }
            }

//            val count = countDownFlow
//                .count {
//                    it % 2 == 0
//                }
//            println("The count is $count")

//            val reduceResult = countDownFlow
//                .reduce { accumulator, value ->
//                    accumulator + value
//                }
//            println("The reduce result is $reduceResult")

//            val foldResult = countDownFlow
//                .fold(100) { accumulator, value ->
//                    accumulator + value
//                }
//            println("The fold result is $foldResult")

//            countDownFlow
//                .filter { time ->
//                    time % 2 == 0
//                }
//                .map { time ->
//                    time * time
//                }
//                .onEach { time ->
//                    println(time)
//                }
//                .collect { time ->
//                    println("The current time is $time")
//
//                }
        }
    }
}


