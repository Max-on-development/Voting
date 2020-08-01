package com.petproject.voting.util;

import com.petproject.voting.model.Meal;
import com.petproject.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Util {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal("Happy meal", 1, 150D, LocalDate.of(2020, 07, 22)),
            new Meal("Chicken set #1", 2, 320D, LocalDate.of(2020, 07, 22)),
            new Meal("Margharita pizza", 3, 220D, LocalDate.of(2020, 07, 22)),
            new Meal("Картофель жареный", 4, 190D, LocalDate.of(2020, 07, 22)),
            new Meal("California roll", 5, 280D, LocalDate.of(2020, 07, 22))
    );

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(
            new Restaurant(1,"McDonald's"),
            new Restaurant(2,"Kentucky Fried Chicken"),
            new Restaurant(3,"Pizza Hut"),
            new Restaurant(4,"Крошка картошка"),
            new Restaurant(5,"Sushi Oki")
    );
}
