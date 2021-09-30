package com.appmarket.config;

import java.util.Map;
record RestErrorResponse(String code, Map<?, ?> errors) {
}
