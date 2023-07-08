package br.com.rocha.springbootandkotlin

import br.com.rocha.springbootandkotlin.exceptions.UnsuportedMathOperationException
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{number1}/{number2}"])
    fun sum(@PathVariable(value="number1") number1: String?,
            @PathVariable(value="number2") number2: String?
            ): Double {
        //u can put Double in PathParam, but i put String because
        //im learning validations
        if(!isNumeric(number1) || !isNumeric(number2))
                throw UnsuportedMathOperationException("Please set a numeric value!")
        return convertToDouble(number1) + convertToDouble(number2)
    }

    private fun convertToDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank()) return 0.0
        val number = strNumber.replace(",".toRegex(),".")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if (strNumber.isNullOrBlank()) return false;
        val number = strNumber.replace(",".toRegex(),".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}