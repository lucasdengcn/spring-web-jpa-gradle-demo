package com.example.demo.employee.apis.handlers

import com.example.demo.employee.apis.response.ErrorResponse
import com.example.demo.employee.exception.RecordNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.*
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    companion object {
        val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    fun getRequestUri(request: WebRequest) : String {
        if (request is ServletWebRequest){
            return request.request.requestURI
        } else {
            return "Unknown"
        }
    }

    fun getTraceId(request: WebRequest) : String? {
        if (request is ServletWebRequest){
            val header = request.getHeader("trace-id")
            return header
        } else {
            return "Unknown"
        }
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(ex: IllegalStateException, request: WebRequest): ResponseEntity<ErrorResponse> {
        logger.error("IllegalState: ", ex)
        //
        val errorResponse = ErrorResponse(
            "IllegalState",
            ex.message!!,
            getRequestUri(request),
            getTraceId(request)
        )
        return wrapResponse(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: RecordNotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        logger.error("RecordNotFoundException: {}", request, ex)
        //
        val errorResponse = ErrorResponse(
            ex.code,
            ex.message,
            getRequestUri(request),
            getTraceId(request)
        )
        return wrapResponse(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleNotAcceptableException(ex: HttpMediaTypeNotAcceptableException, request: WebRequest): ResponseEntity<ErrorResponse> {
        logger.error("RequestNotSupported: ", ex)
        //
        val errorResponse = ErrorResponse(
            "RequestNotSupported",
            ex.message!!,
            getRequestUri(request),
            getTraceId(request)
        )
        return wrapResponse(errorResponse, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    fun handleNotSupportedException(ex: HttpRequestMethodNotSupportedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        logger.error("RequestNotSupported: ", ex)
        //
        val errorResponse = ErrorResponse(
            "RequestNotSupported",
            ex.message!!,
            getRequestUri(request),
            getTraceId(request)
        )
        return wrapResponse(errorResponse, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        logger.error("UnexpectedError: ", ex)
        //
        val errorResponse = ErrorResponse(
            "Request Error",
            ex.message!!,
            getRequestUri(request),
            getTraceId(request)
        )
        return wrapResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        logger.error("RequestInputInvalid: ", ex)
        //
        val errorMessages = arrayListOf("")
        ex.fieldErrors.map { errorMessages += it.defaultMessage!! }
        //
        val errorResponse = ErrorResponse(
            "RequestInputInvalid",
            errorMessages.joinToString(","),
            getRequestUri(request),
            getTraceId(request)
        )
        return wrapResponse(errorResponse, HttpStatus.BAD_REQUEST)
    }

    private fun wrapResponse(errorResponse: ErrorResponse, status: HttpStatus): ResponseEntity<ErrorResponse> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return ResponseEntity(errorResponse, headers, status)
    }

}