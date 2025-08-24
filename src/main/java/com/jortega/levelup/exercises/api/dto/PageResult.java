package com.jortega.levelup.exercises.api.dto;

import java.util.List;

public record PageResult<T>(
        List<T> items,
        long total,
        int page,
        int size,
        int totalPages,
        boolean first,
        boolean last,
        boolean hasNext,
        boolean hasPrevious
) {}