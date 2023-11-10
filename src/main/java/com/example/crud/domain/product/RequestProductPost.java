package com.example.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductPost(@NotBlank String name, @NotNull Integer price_in_cents) {}