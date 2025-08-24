package com.jortega.levelup.exercises.domain;

public record ListExercisesQuery(String search, String primaryMuscle, String equipment, int page, int size) {}
