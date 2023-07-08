package br.com.rocha.springbootandkotlin.exceptions

import java.lang.*

class UnsuportedMathOperationException (exception: String?) : RuntimeException(exception) {

}