package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

public class UserMealsUtil {

  public static void main(String[] args) {
    List<UserMeal> meals = Arrays.asList(
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение",
            100),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0),
        LocalTime.of(12, 0), 2000);
    mealsTo.forEach(System.out::println);

    System.out.println("---------------------------------------");

    List<UserMealWithExcess> mealsToStream = filteredByStreams(meals, LocalTime.of(7, 0),
        LocalTime.of(12, 0), 2000);
    mealsToStream.forEach(System.out::println);
  }

  public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime,
      LocalTime endTime, int caloriesPerDay) {
    // TODO return filtered list with excess. Implement by cycles

    Map<LocalDate, Integer> totalCaloriesPerDay = new HashMap<>();
    for (UserMeal meal : meals) {
      if (totalCaloriesPerDay.containsKey(meal.getDateTime().toLocalDate())) {
        totalCaloriesPerDay.put(meal.getDateTime().toLocalDate(),
            totalCaloriesPerDay.get(meal.getDateTime().toLocalDate()) + meal.getCalories());
      } else {
        totalCaloriesPerDay.put(meal.getDateTime().toLocalDate(), meal.getCalories());
      }
    }
    List<UserMealWithExcess> result = new ArrayList<>();

    for (UserMeal meal : meals) {
      if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
        result.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(),
            meal.getCalories(),
            totalCaloriesPerDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
      }
    }

    return result;
  }

  public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals,
      LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
    // TODO Implement by streams

    Map<LocalDate, Integer> totalCaloriesPerDay = meals.stream()
        .collect(Collectors
            .toMap(meal -> meal.getDateTime().toLocalDate(), UserMeal::getCalories, Integer::sum));

    return meals.stream()
        .filter(
            elm -> TimeUtil.isBetweenHalfOpen(elm.getDateTime().toLocalTime(), startTime, endTime))
        .map(elm -> new UserMealWithExcess(elm.getDateTime(), elm.getDescription(),
            elm.getCalories(),
            totalCaloriesPerDay.get(elm.getDateTime().toLocalDate()) > caloriesPerDay))
        .collect(Collectors.toList());
  }
}
