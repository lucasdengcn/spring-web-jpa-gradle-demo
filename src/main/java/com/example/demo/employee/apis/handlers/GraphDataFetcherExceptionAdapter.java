package com.example.demo.employee.apis.handlers;

import com.example.demo.employee.exception.RecordNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GraphDataFetcherExceptionAdapter extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof RecordNotFoundException) {
            return buildNotFoundError(ex, env);
        }
        if (ex instanceof ConstraintViolationException) {
            return buildInvalidError(ex, env);
        }
        return super.resolveToSingleError(ex, env);
    }

    private static GraphQLError buildNotFoundError(Throwable ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .extensions(Map.of("code", 404))
                //.location(env.getField().getSourceLocation())
                .build();
    }

    private static GraphQLError buildInvalidError(Throwable ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .extensions(Map.of("code", 400))
                //.location(env.getField().getSourceLocation())
                .build();
    }
}
