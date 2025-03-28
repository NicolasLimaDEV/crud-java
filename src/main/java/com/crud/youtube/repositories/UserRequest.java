package com.crud.youtube.repositories;

import java.util.UUID;

public record UserRequest(UUID id, String name, String email) {
}