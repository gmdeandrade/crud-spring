package com.example.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductPut(@NotBlank String id, String name, Integer price_in_cents) {}
