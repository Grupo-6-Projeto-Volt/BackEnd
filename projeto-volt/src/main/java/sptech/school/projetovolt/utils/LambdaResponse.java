package sptech.school.projetovolt.utils;

import java.util.Map;

public record LambdaResponse(
    int status,
    Map<String, Object> params,
    boolean valid,

    String response,
    String result
) {
}
