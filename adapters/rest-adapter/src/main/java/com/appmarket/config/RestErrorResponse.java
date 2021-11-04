package com.appmarket.config;

import java.util.Map;
public record RestErrorResponse(String code, Map<?, ?> errors) {
}
